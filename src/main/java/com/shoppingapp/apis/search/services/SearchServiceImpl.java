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
    public List<Store> getStoresForProduct(String keyword, final double maximumDistance, final Units unit, final TransportMode transportMode, final String userLocation) {
        try {
            List<Store> stores = searchDao.getStores(keyword, OrderBy.NONE);

            return (List<Store>) stores.stream().filter(new Predicate<Store>() {
                @Override
                public boolean test(Store store) {
                    LocationInfo locationInfo = locationService.getDistance(userLocation, store.getAddress(), transportMode);
                    if (unit == Units.MINS) {
                        return locationInfo.getDurationMinutes() <= maximumDistance;
                    } else {
                        return locationInfo.getDistanceMeters() <= maximumDistance;
                    }
                }
            }).sorted(new Comparator<Store>() {
                @Override
                public int compare(Store o1, Store o2) {
                    LocationInfo locationInfoA = locationService.getDistance(userLocation, o1.getAddress(), transportMode);
                    LocationInfo locationInfoB = locationService.getDistance(userLocation, o2.getAddress(), transportMode);
                    if (locationInfoA.getDistanceMeters() - locationInfoB.getDistanceMeters() > 0) return 1;

                    return 0;
                }
            }).collect(Collectors.<Store>toList());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }


    @Override
    public Store getStoreForProductByDistance(String keyword, double maximumDistance, String unit, OrderBy orderBy, String userLocation) {
        List<Store> stores = searchDao.getStores(keyword, orderBy);
        return stores.stream().findFirst().get();
    }

    @Override
    public Store getStoreForProductByTime(String keyword, double maximumTume, String unit, OrderBy orderBy, String userLocation) {
        List<Store> stores = searchDao.getStores(keyword, orderBy);
        return stores.stream().findFirst().get();
    }

    @Override
    public Store getStoreForProduct(String keyword, OrderBy orderBy) {
        List<Store> stores = searchDao.getStores(keyword, orderBy);
        return stores.stream().findFirst().get();
    }

    private double getHours(double mins) {
        return mins / 60;
    }


}

