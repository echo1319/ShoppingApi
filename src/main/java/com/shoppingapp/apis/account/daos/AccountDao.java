package com.shoppingapp.apis.account.daos
        ;

import com.shoppingapp.apis.account.models.ListItem;

import java.util.List;

public interface AccountDao {

    void addItem(String userId, String productId, String storeId, double price);

    void updateItem(String userId, String productId, String storeId, double price);

    void deleteItem(String userId, String productId, String storeId);

    List<ListItem> getProductList(String userId);
}