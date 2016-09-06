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
    public void addItem(String username, String productId, String storeId, double price) {
        jdbcTemplate.update(" INSERT INTO list (username, product_id, store_id,price)" +
                " values (?,?,?,?)", username, productId, storeId, price);
    }

    @Override
    public void updateItem(String username, String productId, String storeId, double price) {
        jdbcTemplate.update(" update list set store_id=?,price=? where username =? and product_id=? ",
                storeId, price, username, productId);
    }

    @Override
    public void deleteItem(String username, String productId, String storeId) {
        jdbcTemplate.update("delete from  list  where username =? and product_id=? and storeId=?",
                username, productId, storeId);
    }

    @Override
    public List<ListItem> getProductList(String username) {
        String sql = "select product_id,store_id, price from list where username==? ";
        return jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper(ListItem.class));
    }
}
