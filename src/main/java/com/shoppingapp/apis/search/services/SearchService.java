package com.shoppingapp.apis.search.services;

import com.shoppingapp.apis.search.model.OrderBy;
import com.shoppingapp.apis.search.model.Store;
import com.shoppingapp.apis.search.model.TransportMode;
import com.shoppingapp.apis.search.model.Units;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */

public interface SearchService {

    List<Store> getStoresForProduct(String keyword, double maximumDistance, Units unit, TransportMode transportMode, String userLocation);

    Store getStoreForProductByDistance(String keyword, double maximumDistance, String unit, OrderBy orderBy, String userLocation);

    Store getStoreForProductByTime(String keyword, double maximumTime, String unit, OrderBy orderBy, String userLocation);

    Store getStoreForProduct(String keyword, OrderBy orderBy);
}
