package com.shoppingapp.apis.purchase.services;

import com.shoppingapp.apis.purchase.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */

public interface OrderService {

    public void placeOrder(Order order);

    public Order getOrder(String orderId);

    public List<Order> getOrdersForUser(String userId);

}
