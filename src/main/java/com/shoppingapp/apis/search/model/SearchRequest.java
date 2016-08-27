package com.shoppingapp.apis.search.model;


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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public int getLimitResults() {
        return limitResults;
    }

    public void setLimitResults(int limitResults) {
        this.limitResults = limitResults;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public long getMaximimDistance() {
        return maximimDistance;
    }

    public void setMaximimDistance(long maximimDistance) {
        this.maximimDistance = maximimDistance;
    }

    public long getMaximumTime() {
        return maximumTime;
    }

    public void setMaximumTime(long maximumTime) {
        this.maximumTime = maximumTime;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }
}

