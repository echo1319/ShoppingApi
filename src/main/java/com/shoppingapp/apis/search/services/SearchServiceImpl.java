package com.shoppingapp.apis.search.services;

import com.shoppingapp.apis.search.daos.SearchDao;
import com.shoppingapp.apis.search.model.LocationInfo;
import com.shoppingapp.apis.search.model.Product;
import com.shoppingapp.apis.search.model.Store;
import com.shoppingapp.apis.search.model.TransportMode;
import com.shoppingapp.apis.search.utils.OrderBy;
import com.shoppingapp.apis.search.utils.Units;
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

            List<Store> stores = searchDao.getStoresForProduct(keyword, orderBy);

            List resultsList = (List<Store>) stores.stream().filter(store -> {
                LocationInfo locationInfo = locationService.getDistance(userLocation, store.getAddress(), transportMode);
                store.setDistance(locationInfo.getDistanceMeters());

                return store.getDistance() <= getMeters(maximumDistance, unit);

            }).sorted(((orderBy.equals(OrderBy.DISTANCE)) ? new DistanceComparator() : new PriceComparator())).collect(Collectors.<Store>toList());

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
                    store.setDistance(locationInfo.getDurationMinutes());

                    return store.getDistance() <= getSeconds(maximumTime, unit);
                }
            }).sorted(((orderBy.equals(OrderBy.DISTANCE)) ? new DistanceComparator() : new PriceComparator())).collect(Collectors.<Store>toList());

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
        @Override
        public int compare(Store o1, Store o2) {
            return Double.compare(o1.getDistance(), o2.getDistance());
        }
    }


    private class PriceComparator implements Comparator<Store> {
        @Override
        public int compare(Store o1, Store o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());

        }
    }


    private double getSeconds(double time, Units unit) {
        if (unit == Units.HOURS) {
            return time * 360;
        } else if (unit == Units.MINS) {
            return time * 60;
        } else if (unit == Units.KM || unit == Units.M) {
            return 0;
        }
        return time;
    }

    private double getMeters(double dist, Units unit) {
        if (unit == Units.M) {
            return dist;
        } else if (unit == Units.KM) {
            return dist * 1000;
        }
        return 0;
    }
}

