package com.shoppingapp.apis.review.daos;

import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */
@Repository
public class ReviewDaoImpl implements ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addRating(Rating rating) {
        /*user_id text,
        product_id text,
        rating_id text,
        rating text
                */
        String sql = "INSERT into  rating values(?,?,?,?)";
        jdbcTemplate.update(sql, rating.getUserId(), rating.getStoreId(), rating.getRatingId(), rating.getRating());
    }

    @Override
    public void addComment(Comment comment) {
        /* user_id text,
        product_id text,
        comment_id text,
        comment text
        date text
        */
        String sql = "INSERT into comment values(?,?,?,?,?)";
        jdbcTemplate.update(sql, comment.getUserId(), comment.getStoreId(), comment.getCommentId(),comment.getComment(), comment.getDate());

    }

    @Override
    public List<Rating> getRatings(String storeId) {
        String sql = "select * from rating where store_id=?";
        return jdbcTemplate.query(sql, new Object[]{storeId}, new BeanPropertyRowMapper(Rating.class));
    }

    @Override
    public List<Comment> getComments(String storeId) {

        String sql = "select * from comment where store_id=?";
        return jdbcTemplate.query(sql, new Object[]{storeId}, new BeanPropertyRowMapper(Comment.class));
    }

}
