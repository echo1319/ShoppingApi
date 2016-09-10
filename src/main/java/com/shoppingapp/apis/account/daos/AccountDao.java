package com.shoppingapp.apis.account.daos;


import com.shoppingapp.apis.account.models.ShoppingItem;

import java.util.List;

public interface AccountDao {

    void addItem(String username, String productId, String storeId, double price);

    void updateItem(String username, String productId, String storeId, double price);

    void deleteItem(String username, String productId);

    List<ShoppingItem> getProductList(String username);
}
