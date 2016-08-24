package com.shoppingapp.apis.purchase.services;

import com.shoppingapp.apis.purchase.daos.OrderDaoImpl;
import com.shoppingapp.apis.purchase.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDaoImpl orderDao;

    public void placeOrder(Order order) {
        orderDao.saveOrder(order);
    }

    public Order getOrder(String orderId) {
        return orderDao.getOrder(orderId);
    }

    public List<Order> getOrdersForUser(String userId) {
        return orderDao.getOrdersForUser(userId);
    }

}
