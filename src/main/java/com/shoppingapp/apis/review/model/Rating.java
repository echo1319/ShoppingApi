package com.shoppingapp.apis.review.model;

import java.util.UUID;

/**
 * Created by eri_k on 8/22/2016.
 */
public class Rating {
    private String rating;
    private String userId;
    private String productId;
    private String ratingId;

    public Rating() {
        this.ratingId = UUID.randomUUID().toString();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }
}
