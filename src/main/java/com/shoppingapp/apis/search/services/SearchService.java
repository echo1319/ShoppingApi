package com.shoppingapp.apis.search.services;

import com.shoppingapp.apis.search.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */

public interface SearchService {


    List<Store> getStoresWithinDistance(String keyword, double maximumDistance, Units unit, TransportMode transportMode, String userLocation, OrderBy orderBy);

    List<Store> getStoresWithinTime(String keyword, double maximumTime, Units unit,
                                    TransportMode transportMode, String userLocation, OrderBy orderBy);

    Store getStoreWithinDistance(String keyword, double maximumDistance, Units unit, TransportMode transportMode, String userLocation, OrderBy orderBy);

    Store getStoreWithinTime(String keyword, double maximumTime, Units unit, TransportMode transportMode, String userLocatio, OrderBy orderBy);

    List<Product> getProducts(String keyword);
}
