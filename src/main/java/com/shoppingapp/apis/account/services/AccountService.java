package com.shoppingapp.apis.account.services;

import com.shoppingapp.apis.account.models.ShoppingItem;

import java.util.List;

public interface AccountService {

    void addItem(String username, String productId, String storeId, double price);

    void updateItem(String username, String productId, String storeId, double price);

    void deleteItem(String username, String productId);

    List<ShoppingItem> getProductList(String username);

}
