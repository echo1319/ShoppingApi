package com.shoppingapp.apis.account.controllers;

import com.shoppingapp.apis.account.models.ListItem;
import com.shoppingapp.apis.account.services.AccountService;
import com.shoppingapp.apis.review.model.Review;
import com.shoppingapp.apis.review.services.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
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
    public void deleteItem(@RequestParam(name = "username") String username,
                           @RequestParam(name = "productId") String productId,
                           @RequestParam(name = "storeId") String storeId) throws Exception {
        accountService.deleteItem(username, productId, storeId);
    }

    @RequestMapping(value = "/account/list/item/update")
    public void deleteItem(@RequestParam(name = "username") String username,
                           @RequestParam(name = "productId") String productId,
                           @RequestParam(name = "storeId") String storeId,
                           @RequestParam(name = "price") double price) throws Exception {
        accountService.updateItem(username, productId, storeId, price);
    }

    @RequestMapping(value = "/account/list/get")
    public List<ListItem> getList(@RequestParam(name = "username") String username) {
        return accountService.getProductList(username);
    }
}