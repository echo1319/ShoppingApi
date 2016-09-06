package com.shoppingapp.apis.review.daos;

import com.shoppingapp.apis.review.model.Review;
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
    public void addReview(Review review) {
        String sql = "WITH new_values (store_id, username, rating,comment,rdate) as ( values ('%s', '%s', %f,'%s','%s') )," +
                " upsert as " +
                " (update review m " +
                " set store_id=nv.store_id, username = nv.username, rating= nv.rating , comment=nv.comment, rdate=nv.rdate " +
                " FROM new_values nv " +
                "WHERE m.store_id = nv.store_id AND m.username=nv.username RETURNING m.*) " +
                "INSERT INTO review (store_id, username, rating, comment,rdate ) " +
                "SELECT store_id, username, rating ,comment, rdate " +
                "FROM new_values WHERE NOT EXISTS " +
                " ( SELECT 1 FROM upsert up " +
                "  WHERE up.store_id = new_values.store_id  AND up.username=new_values.username)";

        System.out.println("Review is " + review.toString());
        sql = String.format(sql, review.getStoreId(), review.getUsername(), review.getRating(), review.getComment(), review.getRdate());
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Review> getReviews(String storeId) {
        String sql = "select * from review where store_id=?";
        return jdbcTemplate.query(sql, new Object[]{storeId}, new BeanPropertyRowMapper(Review.class));
    }

}
