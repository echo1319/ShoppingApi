package com.shoppingapp.apis.purchase.controllers;

import com.shoppingapp.apis.purchase.model.Order;
import com.shoppingapp.apis.purchase.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class PurchaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/add")
    public void addOrder(@RequestParam(value = "order") Order order)
            throws Exception {
        orderService.placeOrder(order);
    }

    @RequestMapping(value = "/order/get/{orderId}")
    public Order getOrder(@PathVariable(value = "orderId") String orderId) throws Exception {
        return orderService.getOrder(orderId);
    }

    //TODO check later!!
    @RequestMapping(value = "/order/getByUser/{username}")
    public List<Order> getOrderByUser(@PathVariable(value = "username") String username) throws Exception {
        return orderService.getOrdersForUser(username);
    }


}
