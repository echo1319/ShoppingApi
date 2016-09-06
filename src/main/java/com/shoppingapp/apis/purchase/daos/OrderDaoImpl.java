package com.shoppingapp.apis.purchase.daos;

import com.shoppingapp.apis.purchase.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveOrder(Order order) {

        jdbcTemplate.update("INSERT INTO orders values (?,?,?,?,?,?)", order.getOrder_id(), order.getOrderDate(),
                order.getShopId(), order.getProductId(), order.getPrice(), order.getusername());
    }

    @Override
    public Order getOrder(String orderId) {
        return (Order) jdbcTemplate.queryForObject(
                "select * from orders where order_id =?", new Object[]{orderId}, new OrderRowMapper());
    }

    @Override
    public List<Order> getOrdersForUser(String username) {
        return (List<Order>) jdbcTemplate.queryForObject(
                "select * from orders where username =?", new Object[]{username},
                new BeanPropertyRowMapper(Order.class));
    }
}
