package com.shoppingapp.apis.review.daos;

import com.shoppingapp.apis.purchase.model.Order;
import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;
import com.shoppingapp.apis.search.model.Product;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */
public interface ReviewDao {

    public void addRating(Rating rating);

    public void addComment(Comment comment);

    public List<Rating> getRatings(String storeId);

    public List<Comment> getComments(String storeId);

}
