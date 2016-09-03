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
    public void addReview(Review review) {
        reviewDao.addReview(review);
    }

    @Override
    public List<Review> getReviews(String storeId) {
        return reviewDao.getReviews(storeId);
    }
}
