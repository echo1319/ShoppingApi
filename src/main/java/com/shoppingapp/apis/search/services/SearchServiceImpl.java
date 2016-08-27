package com.shoppingapp.apis.search.services;

import com.shoppingapp.apis.search.daos.SearchDao;
import com.shoppingapp.apis.search.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Autowired
    private LocationServiceImpl locationService;

    //TODO add geolocation hecks here

    @Override
    public List<Store> getStoresWithinDistance(String keyword, final double maximumDistance, final Units unit, final TransportMode transportMode, final String userLocation, final OrderBy orderBy) {
        try {

            List<Store> stores = searchDao.getStores(keyword, OrderBy.NONE);

            return (List<Store>) stores.stream().filter(new Predicate<Store>() {
                @Override
                public boolean test(Store store) {
                    LocationInfo locationInfo = locationService.getDistance(userLocation, store.getAddress(), transportMode);
                    if (unit.equals(Units.KM)) {
                        return locationInfo.getDistanceMeters() <= getMeters(maximumDistance);
                    } else {
                        return locationInfo.getDistanceMeters() <= maximumDistance;
                    }
                }
            }).sorted(((orderBy.equals(OrderBy.DISTANCE)) ? new DistanceComparator(userLocation, transportMode) : new PriceComparator())).collect(Collectors.<Store>toList());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }


    @Override
    public List<Store> getStoresWithinTime(String keyword, final double maximumTime, final Units unit,
                                           final TransportMode transportMode, final String userLocation, OrderBy orderBy) {
        try {
            List<Store> stores = searchDao.getStores(keyword, OrderBy.NONE);
            return (List<Store>) stores.stream().filter(new Predicate<Store>() {
                @Override
                public boolean test(Store store) {
                    LocationInfo locationInfo = locationService.getDistance(userLocation, store.getAddress(), transportMode);
                    if (unit.equals(Units.HOURS)) {
                        return locationInfo.getDurationMinutes() <= getMins(maximumTime);
                    } else {
                        return locationInfo.getDurationMinutes() <= maximumTime;
                    }
                }
            }).sorted(((orderBy.equals(OrderBy.DISTANCE)) ? new TimeComparator(userLocation, transportMode) : new PriceComparator())).collect(Collectors.<Store>toList());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }


    @Override
    public Store getStoreWithinDistance(String keyword, final double maximumDistance, final Units unit, final TransportMode transportMode, final String userLocation, OrderBy orderBy) {

        return getStoresWithinDistance(keyword, maximumDistance, unit, transportMode, userLocation, orderBy).stream().findFirst().get();

    }

    @Override
    public Store getStoreWithinTime(String keyword, double maximumTime, Units unit, TransportMode transportMode, String userLocation, OrderBy orderBy) {
        return getStoresWithinTime(keyword, maximumTime, unit, transportMode, userLocation, orderBy).stream().findFirst().get();

    }


    @Override
    public List<Product> getProducts(String keyword) {
        List<Product> products = searchDao.getProduct(keyword);
        return products;
    }


    private class DistanceComparator implements Comparator<Store> {
        private String userLocation;
        private TransportMode transportMode;

        public DistanceComparator(String userLocation, TransportMode transportMode) {
            this.userLocation = userLocation;
            this.transportMode = transportMode;
        }

        ;

        @Override
        public int compare(Store o1, Store o2) {
            LocationInfo locationInfoA = locationService.getDistance(userLocation, o1.getAddress(), transportMode);
            LocationInfo locationInfoB = locationService.getDistance(userLocation, o2.getAddress(), transportMode);
            if (locationInfoA.getDistanceMeters() - locationInfoB.getDistanceMeters() > 0) {
                return 1;
            }
            return 0;

        }
    }

    private class TimeComparator implements Comparator<Store> {
        private String userLocation;
        private TransportMode transportMode;

        public TimeComparator(String userLocation, TransportMode transportMode) {
            this.userLocation = userLocation;
            this.transportMode = transportMode;
        }

        ;

        @Override
        public int compare(Store o1, Store o2) {
            LocationInfo locationInfoA = locationService.getDistance(userLocation, o1.getAddress(), transportMode);
            LocationInfo locationInfoB = locationService.getDistance(userLocation, o2.getAddress(), transportMode);
            if (locationInfoA.getDurationMinutes() - locationInfoB.getDurationMinutes() > 0) {
                return 1;
            }
            return 0;

        }
    }

    private class PriceComparator implements Comparator<Store> {
        @Override
        public int compare(Store o1, Store o2) {
            if (o1.getPrice() - o2.getPrice() > 0) {
                return 1;
            }
            return 0;
        }
    }


    private double getMins(double Hours) {
        return Hours * 60;
    }

    private double getMeters(double KM) {
        return KM * 1000;
    }

}

