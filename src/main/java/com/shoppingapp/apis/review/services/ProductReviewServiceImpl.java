package com.shoppingapp.apis.review.services;

import com.shoppingapp.apis.review.daos.ReviewDao;
import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Override
    public void addComment(String body, String userId, String productId, String date) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setProductId(productId);
        comment.setComment(body);
        comment.setDate(date);
        reviewDao.addComment(comment);
    }

    @Override
    public List<Comment> getComments(String productId) {
        return reviewDao.getComments(productId);

    }

    @Override
    public void addRating(String productId, String rate, String userId) {
        Rating rating = new Rating();
        rating.setProductId(productId);
        rating.setUserId(userId);
        rating.setRating(rate);
        reviewDao.addRating(rating);

    }

    @Override
    public List<Rating> getRatings(String productId) {
        return reviewDao.getRatings(productId);
    }

}
