package com.shoppingapp.apis.auth.daos;

import com.shoppingapp.apis.auth.model.User;

/**
 * Created by eri_k on 8/23/2016.
 */
public interface AuthenticationDao {
    User login(String username, String password);
    void register(User user) throws Exception;

}
