package com.shoppingapp.apis.review.controllers;

import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;
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

    @RequestMapping(value = "/store/{storeId}/comment", method = RequestMethod.POST)
    public void addComment(@RequestParam(value = "comment") String comment, @RequestParam(value = "userId") String userId, @PathVariable(value = "storeId") String storeId,
                           @RequestParam(value = "date") String date)
            throws Exception {
        storeReviewService.addComment(comment, userId, storeId, date);

    }

    @RequestMapping(value = "/store/{storeId}/comment/get")
    public List<Comment> getComments(@PathVariable(value = "storeId") String storeId) throws Exception {
        return storeReviewService.getComments(storeId);
    }

    @RequestMapping(value = "/store/{storeId}/rate", method = RequestMethod.POST)
    public void addRating(@PathVariable(value = "storeId") String storeId, @RequestParam(value = "rating") String rating, @RequestParam(value = "userId") String userId) {
        storeReviewService.addRating(storeId, rating, userId);
    }

    @RequestMapping(value = "/store/{storeId}/rate/get", method = RequestMethod.GET)
    public List<Rating> addRating(@PathVariable(value = "storeId") String storeId) {
        return storeReviewService.getRatings(storeId);
    }


}
