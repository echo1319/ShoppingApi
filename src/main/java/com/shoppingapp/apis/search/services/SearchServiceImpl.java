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

    @Override
    public List<Store> getStoresWithinDistance(String keyword, final double maximumDistance, final Units unit, final TransportMode transportMode, final String userLocation, final OrderBy orderBy, int maxResults) {
        try {

            List<Store> stores = searchDao.getStoresForProduct(keyword, OrderBy.NONE);

            List resultsList = (List<Store>) stores.stream().filter(store -> {
                LocationInfo locationInfo = locationService.getDistance(userLocation, store.getAddress(), transportMode);
                if (unit.equals(Units.KM)) {
                    return locationInfo.getDistanceMeters() <= getMeters(maximumDistance);
                } else {
                    return locationInfo.getDistanceMeters() <= maximumDistance;
                }
            }).sorted(((orderBy.equals(OrderBy.DISTANCE)) ? new DistanceComparator(userLocation, transportMode) : new PriceComparator())).collect(Collectors.<Store>toList());

            if (maxResults > 0) {
                return resultsList.subList(0, maxResults);
            }

            return resultsList;


        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }


    @Override
    public List<Store> getStoresWithinTime(String keyword, final double maximumTime, final Units unit,
                                           final TransportMode transportMode, final String userLocation, OrderBy orderBy, int maxResults) {
        try {
            List<Store> stores = searchDao.getStoresForProduct(keyword, OrderBy.NONE);

            List resultsList = (List<Store>) stores.stream().filter(new Predicate<Store>() {
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

            if (maxResults > 0) {
                return resultsList.subList(0, maxResults);
            }

            return resultsList;


        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public List<Product> getProducts(String keyword, String category, OrderBy orderBy) {
        return searchDao.getProduct(keyword, category, orderBy);
    }

    @Override
    public List<Store> getStores(String keyword, OrderBy orderBy) {

        return searchDao.getStores(keyword, orderBy);
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
            o1.setDistance(locationInfoA.getDistanceMeters());
            o2.setDistance(locationInfoB.getDistanceMeters());
            return Double.compare(locationInfoA.getDistanceMeters(), locationInfoB.getDistanceMeters());
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
            o1.setDistance(locationInfoA.getDurationMinutes());
            o1.setDistance(locationInfoB.getDurationMinutes());

            return Double.compare(locationInfoA.getDurationMinutes(), locationInfoB.getDurationMinutes());

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

