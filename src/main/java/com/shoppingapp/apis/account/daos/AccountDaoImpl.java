package com.shoppingapp.apis.account.daos;

import com.shoppingapp.apis.account.models.ListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addItem(String userId, String productId, String storeId, double price) {
        jdbcTemplate.update("INSERT INTO list (user_id, product_id, store_id,price)" +
                " values (?,?,?,?)", userId, productId, storeId, price);
    }

    @Override
    public void updateItem(String userId, String productId, String storeId, double price) {
        jdbcTemplate.update("update list set store_id=?,price=? where user_id =? and product_id=?",
                storeId, price, userId, productId);
    }

    @Override
    public void deleteItem(String userId, String productId, String storeId) {
        jdbcTemplate.update("delete from  list  where user_id =? and product_id=? and storeId=?",
                userId, productId, storeId);
    }

    @Override
    public List<ListItem> getProductList(String userId) {
        String sql = "select product_id,store_id, price from list where user_id==? ";
        return jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper(ListItem.class));
    }
}
