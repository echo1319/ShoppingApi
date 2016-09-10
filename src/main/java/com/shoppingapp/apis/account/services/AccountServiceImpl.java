package com.shoppingapp.apis.account.services;

import com.shoppingapp.apis.account.daos.AccountDao;
import com.shoppingapp.apis.account.models.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public void addItem(String username, String productId, String storeId, double price) {
        accountDao.addItem(username, productId, storeId, price);
    }

    @Override
    public void updateItem(String username, String productId, String storeId, double price) {
        accountDao.updateItem(username, productId, storeId, price);
    }

    @Override
    public void deleteItem(String username, String productId) {
        accountDao.deleteItem(username, productId);
    }

    @Override
    public List<ShoppingItem> getProductList(String username) {
        return accountDao.getProductList(username);
    }
}
