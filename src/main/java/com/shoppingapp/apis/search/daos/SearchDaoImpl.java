package com.shoppingapp.apis.search.daos;

import com.shoppingapp.apis.purchase.model.Order;
import com.shoppingapp.apis.search.model.OrderBy;
import com.shoppingapp.apis.search.model.Product;
import com.shoppingapp.apis.search.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SearchDaoImpl implements SearchDao {

    private JdbcTemplate jdbcTemplate;

    //TODO capitalize-ignore case in  search string
    @Autowired
    public SearchDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Store> getStoresForProduct(String keyword, OrderBy orderBy) {

        String sql = "select  s.store_id ,s.name ,s.address ,s.country , sp.price " +
                " from product p ,store_product sp, store s" +
                " where p.name like '%" + keyword + "%' and sp.store_id=s.store_id " +
                "and sp.product_id= p.product_id ";

        if (orderBy == OrderBy.PRICE) {
            sql += " order by p.price asc ";
        }

        List<Store> stores = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Store.class));

        return stores;
    }

    @Override
    public List<Store> getStores(String keyword, OrderBy orderBy) {

        String sql = "select * from store s where s.name like '%" + keyword + "%'";

        if (orderBy == OrderBy.NAME) {
            sql += " order by s.name asc ";
        }

        List<Store> stores = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Store.class));
        return stores;
    }


    @Override
    public List<Product> getProduct(String keyword, String category, OrderBy orderBy) {

        String sql = "select  * from product p  where p.name like '%" + keyword + "%'";

        if (!category.isEmpty()) {
            sql += " GROUP by p.category ";
        }

        if (orderBy == OrderBy.CATEGORY) {
            sql += " ORDER by p." + OrderBy.CATEGORY + " asc ";
        } else if (orderBy == OrderBy.NAME) {
            sql += " ORDER by p." + OrderBy.NAME + " asc ";
        }


        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Product.class));

        return products;

    }


}
