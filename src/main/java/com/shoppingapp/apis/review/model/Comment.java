package com.shoppingapp.apis.review.model;

import java.util.UUID;

/**
 * Created by eri_k on 8/22/2016.
 */
public class Comment {
    private String userId;
    private String storeId;
    private String commentId;
    private String comment;
    private String date;

    public Comment() {
        this.commentId = UUID.randomUUID().toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
