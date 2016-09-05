package com.shoppingapp.apis.search.services;

import com.shoppingapp.apis.search.model.*;
import com.shoppingapp.apis.search.utils.OrderBy;
import com.shoppingapp.apis.search.utils.Units;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */

public interface SearchService {

    List<Store> getStoresWithinDistance(String keyword, double maximumDistance, Units unit, TransportMode transportMode, String userLocation, OrderBy orderBy, int maxResults);

    List<Store> getStoresWithinTime(String keyword, double maximumTime, Units unit, TransportMode transportMode, String userLocation, OrderBy orderBy, int maxResults);

    List<Product> getProducts(String keyword, String category, OrderBy orderBy);

    List<Store> getStores(String keyword, OrderBy orderBy);
}
