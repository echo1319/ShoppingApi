package com.shoppingapp.apis.search.controllers;

import com.shoppingapp.apis.purchase.model.Order;
import com.shoppingapp.apis.purchase.services.OrderService;
import com.shoppingapp.apis.search.model.*;
import com.shoppingapp.apis.search.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search/stores")
    public List<Store> getStoresForProductByDistance(@RequestParam(value = "keywords") String keyword,
                                                     @RequestParam(value = "maximumDistance", defaultValue = "-1") double maximumDistance,
                                                     @RequestParam(value = "maximumDistanceUnit", defaultValue = "-1") String maximumDistanceUnit,
                                                     @RequestParam(value = "userLocation", defaultValue = "") String userLocation,
                                                     @RequestParam(value = "transportMode", defaultValue = "DRIVING") String transportMode,
                                                     @RequestParam(value = "orderBy", defaultValue = "DISTANCE") String orderBy) {
        return searchService.getStoresWithinDistance(keyword, maximumDistance, Units.valueOf(maximumDistanceUnit), TransportMode.valueOf(transportMode), userLocation, OrderBy.valueOf(orderBy));
    }

    @RequestMapping(value = "/search/stores")
    public List<Store> getStoresForProductByTime(@RequestParam(value = "keywords") String keyword,
                                                 @RequestParam(value = "maximumTime", defaultValue = "-1") double maximumTime,
                                                 @RequestParam(value = "maximumTimeUnit", defaultValue = "-1") String maximumTimeUnit,
                                                 @RequestParam(value = "userLocation", defaultValue = " ") String userLocation,
                                                 @RequestParam(value = "transportMode", defaultValue = "DRIVING") String transportMode,
                                                 @RequestParam(value = "orderBy", defaultValue = "DISTANCE") String orderBy) {
        return searchService.getStoresWithinTime(keyword, maximumTime, Units.valueOf(maximumTimeUnit), TransportMode.valueOf(transportMode), userLocation, OrderBy.valueOf(orderBy));

    }

    @RequestMapping(value = "/search/stores")
    public Store getStoreForProductByDistance(@RequestParam(value = "keywords") String keyword,
                                              @RequestParam(value = "maximumDistance", defaultValue = "-1") double maximumDistance,
                                              @RequestParam(value = "maximumDistanceUnit", defaultValue = "-1") String maximumDistanceUnit,
                                              @RequestParam(value = "userLocation", defaultValue = " ") String userLocation,
                                              @RequestParam(value = "transportMode", defaultValue = "DRIVING") String transportMode,
                                              @RequestParam(value = "orderBy", defaultValue = "PRICE") String orderBy) {
        return searchService.getStoreWithinDistance(keyword, maximumDistance, Units.valueOf(maximumDistanceUnit), TransportMode.valueOf(transportMode), userLocation, OrderBy.valueOf(orderBy));
    }


    @RequestMapping(value = "/search/stores")
    public Store getStoreForProductByTime(@RequestParam(value = "keywords") String keyword,
                                          @RequestParam(value = "maximumTime", defaultValue = "-1") double maximumTime,
                                          @RequestParam(value = "maximumTimeUnit", defaultValue = "-1") String maximumTimeUnit,
                                          @RequestParam(value = "userLocation", defaultValue = " ") String userLocation,
                                          @RequestParam(value = "transportMode", defaultValue = "DRIVING") String transportMode,
                                          @RequestParam(value = "orderBy", defaultValue = "PRICE") String orderBy) {
        return searchService.getStoreWithinTime(keyword, maximumTime, Units.valueOf(maximumTimeUnit), TransportMode.valueOf(transportMode), userLocation, OrderBy.valueOf(orderBy));

    }


    @RequestMapping(value = "/search/products")
    public List<Product> getProducts(@RequestParam(value = "keywords") String keyword) {
        {
            return searchService.getProducts(keyword);
        }


    }

}
