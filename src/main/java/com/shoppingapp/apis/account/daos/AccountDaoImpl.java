package com.shoppingapp.apis.account.daos;

import com.shoppingapp.apis.account.models.ShoppingItem;
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
    public void deleteItem(String username, String productId) {
        jdbcTemplate.update("delete from  list  where username =? and product_id=? ",
                username, productId);
    }

    @Override
    public List<ShoppingItem> getProductList(String username) {
        String sql = "select p.product_id as productId, s.store_id as storeId" +
                ", p.name as productName, p.description as productDescription, p.category as productCategory" +
                " , s.name as storeName , s.country as storeCountry , sp.price  as storePrice, s.address as storeAddress" +
                "  from list l, product p, store s  , store_product sp " +
                " where l.product_id=p.product_id and l.store_id=s.store_id and  sp.store_id=s.store_id and p.product_id =sp.product_id " +
                "and  l.username=? ";

        return jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper(ShoppingItem.class));
    }

}

