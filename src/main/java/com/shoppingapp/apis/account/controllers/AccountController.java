package com.shoppingapp.apis.account.controllers;

import com.shoppingapp.apis.account.models.ShoppingItem;
import com.shoppingapp.apis.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/account/list/item/add", method = RequestMethod.POST)
    public void addItem(@RequestParam(name = "username") String username,
                        @RequestParam(name = "productId") String productId,
                        @RequestParam(name = "storeId") String storeId,
                        @RequestParam(name = "price") double price) throws Exception {
        accountService.addItem(username, productId, storeId, price);
    }

    @RequestMapping(value = "/account/list/item/delete")
    public void deleteItem(@RequestParam(name = "username") String username, @RequestParam(name = "productId") String productId) throws Exception {
        accountService.deleteItem(username, productId);
    }

    @RequestMapping(value = "/account/list/item/update")
    public void deleteItem(@RequestParam(name = "username") String username,
                           @RequestParam(name = "productId") String productId,
                           @RequestParam(name = "storeId") String storeId,
                           @RequestParam(name = "price") double price) throws Exception {
        accountService.updateItem(username, productId, storeId, price);
    }

    @RequestMapping(value = "/account/list/get")
    public List<ShoppingItem> getList(@RequestParam(name = "username") String username) {
        return accountService.getProductList(username);
    }
}