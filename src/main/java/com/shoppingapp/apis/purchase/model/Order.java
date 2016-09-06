package com.shoppingapp.apis.purchase.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by eri_k on 8/22/2016.
 */
public class Order implements Serializable {
    private String order_id;
    private String orderDate;
    private String shopId;
    private String productId;
    private String productName;
    private double price;
    private String username;

    public Order() {
        this.setOrder_id(UUID.randomUUID().toString());
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }
}
