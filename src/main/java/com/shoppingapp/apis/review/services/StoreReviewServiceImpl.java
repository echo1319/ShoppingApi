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
public class StoreReviewServiceImpl implements StoreReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Override
    public void addComment(String body, String userId, String storeId, String date) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setStoreId(storeId);
        comment.setComment(body);
        comment.setDate(date);
        reviewDao.addComment(comment);
    }

    @Override
    public List<Comment> getComments(String storeId) {
        return reviewDao.getComments(storeId);

    }

    @Override
    public void addRating(String storeId, String rate, String userId) {
        Rating rating = new Rating();
        rating.setStoreId(storeId);
        rating.setUserId(userId);
        rating.setRating(rate);
        reviewDao.addRating(rating);

    }

    @Override
    public List<Rating> getRatings(String storeId) {
        return reviewDao.getRatings(storeId);
    }

}
