package com.shoppingapp.apis.review.services;

import com.shoppingapp.apis.review.daos.ReviewDao;
import com.shoppingapp.apis.review.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreReviewServiceImpl implements StoreReviewService {
    @Autowired
    private ReviewDao reviewDao;


    @Override
    public void addReview(String userId, String storeId, String date, String comment, Double rating) {
        Review review = new Review();
        review.setUserId(userId);
        review.setShopId(storeId);
        review.setComment(comment);
        review.setRating(rating);
        review.setDate(date);
        reviewDao.addReview(review);
    }

    @Override
    public List<Review> getReviews(String storeId) {
        return reviewDao.getReviews(storeId);
    }
}
