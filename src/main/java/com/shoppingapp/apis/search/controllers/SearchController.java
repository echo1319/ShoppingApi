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

    @RequestMapping(value = "/search")
    public List<Store> getStoresForProductByDistance(@RequestParam(value = "keywords") String keyword,
                                                     @RequestParam(value = "distance", defaultValue = "0") double maximumDistance,
                                                     @RequestParam(value = "unit", defaultValue = "0") String unit,
                                                     @RequestParam(value = "duration", defaultValue = "0") double maximumTime,
                                                     @RequestParam(value = "userLocation") String userLocation,
                                                     @RequestParam(value = "transportMode", defaultValue = "DRIVING") String transportMode,
                                                     @RequestParam(value = "orderBy", defaultValue = "DISTANCE") String orderBy,
                                                     @RequestParam(value = "maxResults", defaultValue = "0") int maxResults) {
        if (maximumTime > 0) {
            return searchService.getStoresWithinTime(keyword, maximumTime, Units.valueOf(unit), TransportMode.valueOf(transportMode), userLocation, OrderBy.valueOf(orderBy), maxResults);
        } else if (maximumDistance > 0) {
            return searchService.getStoresWithinDistance(keyword, maximumDistance, Units.valueOf(unit), TransportMode.valueOf(transportMode), userLocation, OrderBy.valueOf(orderBy), maxResults);
        }
        return null;
    }


    @RequestMapping(value = "/search/products")
    public List<Product> getProducts(@RequestParam(value = "keywords") String keyword, @RequestParam(value = "category", defaultValue = "") String category,
                                     @RequestParam(value = "orderBy", defaultValue = "NAME") String orderBy) {
        {
            //TODO  add sorting by category
            return searchService.getProducts(keyword, category, OrderBy.valueOf(orderBy));
        }
    }

    @RequestMapping(value = "/search/stores")
    public List<Store> getProducts(@RequestParam(value = "keywords") String keyword, @RequestParam(value = "orderBy", defaultValue = "NAME") String orderBy) {
        {
            return searchService.getStores(keyword, OrderBy.valueOf(orderBy));
        }
    }
}
