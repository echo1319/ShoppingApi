package com.shoppingapp.apis.review.services;

import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */
public interface StoreReviewService {
    void addComment(String body, String userId, String storeId, String date);

    List<Comment> getComments(String storeId);

    void addRating(String storeId, String rate, String userId);

    List<Rating> getRatings(String storeId);
}
