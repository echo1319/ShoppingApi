package com.shoppingapp.apis.review.daos;

import com.shoppingapp.apis.review.model.Comment;
import com.shoppingapp.apis.review.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


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
        rating text                */


        String sql = "WITH new_values (store_id, user_id, rating) as ( values ('%s', '%s', '%s') ," +
                " upsert as " +
                " (update rating m " +
                " set store_id=nv.store_id, user_id = nv.user_id, rating= nv.rating " +
                " FROM new_values nv " +
                "WHERE m.store_id = nv.store_id AND m.user_id=nv.user_id RETURNING m.*) " +
                "INSERT INTO rating (store_id, user_id, rating) " +
                "SELECT store_id, user_id, rating " +
                "FROM new_values  WHERE NOT EXISTS " +
                "(SELECT 1 FROM upsert up" +
                " WHERE up.store_id = new_values.store_id  AND up.user_id=new_values.user_id)";

        sql = String.format(sql, rating.getUserId(), rating.getStoreId(), rating.getRating());
        jdbcTemplate.update(sql);

    }

    @Override
    public void addComment(Comment comment) {
        /* user_id text,
        product_id text,
        comment_id text,
        comment text
        date text
        */
        String sql = "insert into comment values(?,?,?,?,?)";
        jdbcTemplate.update(sql, comment.getUserId(), comment.getStoreId(), comment.getCommentId(), comment.getComment(), comment.getDate());

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
