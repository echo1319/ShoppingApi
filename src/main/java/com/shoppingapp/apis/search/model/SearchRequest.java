package com.shoppingapp.apis.search.model;


import com.shoppingapp.apis.search.utils.OrderBy;
import com.shoppingapp.apis.search.utils.Units;

public class SearchRequest {
    private String keywords;
    private OrderBy orderBy;
    private int limitResults;
    private TransportMode transportMode;
    private Units units;
    private long maximimDistance;
    private long maximumTime;
    private String userLocation;
    private int maxResults;


}

