package com.shoppingapp.apis.review.daos;

import com.shoppingapp.apis.review.model.Review;

import java.util.List;

public interface ReviewDao {
    void addReview(Review review);

    List<Review> getReviews(String storeId);
}
