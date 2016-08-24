package com.shoppingapp.apis.review.services;

import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */
public interface ProductReviewService {
    void addComment(String body, String userId, String productId, String date);

    List<Comment> getComments(String productId);

    void addRating(String productId, String rate, String userId);

    List<Rating> getRatings(String productId);
}
