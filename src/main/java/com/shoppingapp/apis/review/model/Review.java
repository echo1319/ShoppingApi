package com.shoppingapp.apis.review.model;

import java.io.Serializable;

public class Review implements Serializable {
    private String comment;
    private double rating;
    private String userId;
    private String storeId;
    private String rdate;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    @Override
    public String toString() {
        return "Review{ comment='" + comment + '\'' +
                ", rating=" + rating +
                ", userId='" + userId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", rdate='" + rdate + '\'' +
                '}';
    }
}
