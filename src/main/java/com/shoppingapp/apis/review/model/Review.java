package com.shoppingapp.apis.review.model;

import java.io.Serializable;

public class Review implements Serializable {
    private String comment;
    private double rating;
    private String username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", username='" + username + '\'' +
                ", storeId='" + storeId + '\'' +
                ", rdate='" + rdate + '\'' +
                '}';
    }
}
