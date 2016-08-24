package com.shoppingapp.apis.auth.services;

import com.shoppingapp.apis.auth.daos.AuthenticationDao;
import com.shoppingapp.apis.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationDao authenticationDao;

    @Override
    public User login(String username, String password) {
        return authenticationDao.login(username, password);

    }

    @Override
    public void register(User user) throws Exception {
        authenticationDao.register(user);

    }
}
