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
    public void addItem(@RequestParam(name = "userId") String userId,
                        @RequestParam(name = "productId") String productId,
                        @RequestParam(name = "storeId") String storeId,
                        @RequestParam(name = "price") double price) throws Exception {
        accountService.addItem(userId, productId, storeId, price);
    }

    @RequestMapping(value = "/account/list/item/delete")
    public void deleteItem(@RequestParam(name = "userId") String userId,
                           @RequestParam(name = "productId") String productId,
                           @RequestParam(name = "storeId") String storeId) throws Exception {
        accountService.deleteItem(userId, productId, storeId);
    }

    @RequestMapping(value = "/account/list/item/update")
    public void deleteItem(@RequestParam(name = "userId") String userId,
                           @RequestParam(name = "productId") String productId,
                           @RequestParam(name = "storeId") String storeId,
                           @RequestParam(name = "price") double price) throws Exception {
        accountService.updateItem(userId, productId, storeId, price);
    }

    @RequestMapping(value = "/account/list/get")
    public List<ListItem> getList(@RequestParam(name = "userId") String userId) {
        return accountService.getProductList(userId);
    }
}