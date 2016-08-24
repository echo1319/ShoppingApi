package com.shoppingapp.apis.review.controllers;

import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;
import com.shoppingapp.apis.review.services.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class ProductReviewController {
    @Autowired
    private ProductReviewService productReviewService;

    @RequestMapping(value = "/product/{productId}/comment", method = RequestMethod.POST)
    public void addComment(@RequestParam(value = "comment") String comment, @RequestParam(value = "userId") String userId, @PathVariable(value = "productId") String productId, @RequestParam(value = "date") String date)
            throws Exception {
        productReviewService.addComment(comment, userId, productId, date);

    }

    @RequestMapping(value = "/product/{productId}/comment/get")
    public List<Comment> getComments(@PathVariable(value = "productId") String productId) throws Exception {
        return productReviewService.getComments(productId);
    }

    @RequestMapping(value = "/product/{productId}/rate", method = RequestMethod.POST)
    public void addRating(@PathVariable(value = "productId") String productId, @RequestParam(value = "rating") String rating, @RequestParam(value = "userId") String userId) {
        productReviewService.addRating(productId, rating, userId);
    }

    @RequestMapping(value = "/product/{productId}/rate/get", method = RequestMethod.GET)
    public List<Rating> addRating(@PathVariable(value = "productId") String productId) {
        return productReviewService.getRatings(productId);
    }


}
