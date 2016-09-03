package com.shoppingapp.apis.review.services;

import com.shoppingapp.apis.review.model.Review;

import java.util.List;


public interface StoreReviewService {

    void addReview(Review review);

    List<Review> getReviews(String storeId);

}
