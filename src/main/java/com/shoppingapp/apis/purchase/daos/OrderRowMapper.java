package com.shoppingapp.apis.purchase.daos;


import com.shoppingapp.apis.purchase.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrder_id(rs.getString(1));
        order.setOrderDate(rs.getString(2));
        order.setShopId(rs.getString(3));
        order.setProductId(rs.getString(4));
        order.setPrice(Double.valueOf(rs.getString(5)));
        order.setUserId(rs.getString(6));

        return order;
    }
/*    order_id text NOT NULL,
    "orderDate" text,
    shop_id text,
    product_id text,
    price text,
    user_id text*/

}
