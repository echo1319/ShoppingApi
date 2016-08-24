package com.shoppingapp.apis.search.controllers;

import com.shoppingapp.apis.purchase.model.Order;
import com.shoppingapp.apis.purchase.services.OrderService;
import com.shoppingapp.apis.search.model.OrderBy;
import com.shoppingapp.apis.search.model.Store;
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

    //TODO check later how to pass order by value
    @RequestMapping(value = "/search")
    public Store getStoreForProduct(@RequestParam(value = "keywords") String keyword,
                                    @RequestParam(value = "maximumDistance", defaultValue = "-1") double maximumDistance,
                                    @RequestParam(value = "maximumDistanceUnit", defaultValue = "-1") String maximumDistanceUnit,
                                    @RequestParam(value = "maximumTime", defaultValue = "-1") double maximumTime,
                                    @RequestParam(value = "maximumTimeUnit", defaultValue = "-1") String maximumTimeUnit,
                                    @RequestParam(value = "userLocation", defaultValue = " ") String userLocation,
                                    @RequestParam(value = "orderBy", defaultValue = "PRICE") String orderBy) {

        if (maximumDistance >= 0) {
            return searchService.getStoreForProductByDistance(keyword, maximumDistance, maximumDistanceUnit, OrderBy.valueOf(orderBy), userLocation);
        }

        if (maximumTime >= 0) {
            return searchService.getStoreForProductByTime(keyword, maximumTime, maximumTimeUnit, OrderBy.valueOf(orderBy), userLocation);
        }

        return searchService.getStoreForProduct(keyword, OrderBy.valueOf(orderBy));
    }


}
