package com.shoppingapp.apis.review.services;

import com.shoppingapp.apis.review.model.Review;

import java.util.List;


public interface StoreReviewService {

    void addReview(String userId, String storeId, String date, String comment, Double rating);

    List<Review> getReviews(String storeId);

}
