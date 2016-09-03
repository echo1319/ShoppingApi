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
    public void addComment(@RequestParam(value = "comment") String comment,
                           @RequestParam(value = "comment") double rating,
                           @RequestParam(value = "userId") String userId,
                           @PathVariable(value = "storeId") String storeId,
                           @RequestParam(value = "date") String date)
            throws Exception {
        storeReviewService.addReview(userId, storeId, date, comment, rating);
    }

    @RequestMapping(value = "/store/{storeId}/review/get")
    public List<Review> getComments(@PathVariable(value = "storeId") String storeId) throws Exception {
        return storeReviewService.getReviews(storeId);
    }


}
