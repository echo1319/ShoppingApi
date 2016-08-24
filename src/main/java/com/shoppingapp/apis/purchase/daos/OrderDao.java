package com.shoppingapp.apis.purchase.daos;

import com.shoppingapp.apis.purchase.model.Order;

import java.util.List;

/**
 * Created by eri_k on 8/22/2016.
 */

public interface OrderDao {

    public void saveOrder(Order order);

    public Order getOrder(String orderId);

    public List<Order> getOrdersForUser(String userId);
}
