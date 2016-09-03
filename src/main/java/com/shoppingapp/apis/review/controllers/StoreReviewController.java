package com.shoppingapp.apis.review.controllers;

import com.shoppingapp.apis.review.model.Review;
import com.shoppingapp.apis.review.services.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class StoreReviewController {
    @Autowired
    private StoreReviewService storeReviewService;

    @RequestMapping(value = "/store/{storeId}/review", method = RequestMethod.POST)
    public void addReview(@RequestBody Review review, @PathVariable String storeId)
            throws Exception {
        storeReviewService.addReview(review);
    }

    @RequestMapping(value = "/store/{storeId}/review/get", method = RequestMethod.GET)
    public List<Review> getReviews(@PathVariable String storeId) throws Exception {
        return storeReviewService.getReviews(storeId);
    }


}
