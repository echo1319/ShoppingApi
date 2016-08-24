package com.shoppingapp.apis.auth.services;

import com.shoppingapp.apis.auth.model.User;

public interface AuthenticationService {

    User login(String username, String password);

    void register(User user) throws Exception;
}
