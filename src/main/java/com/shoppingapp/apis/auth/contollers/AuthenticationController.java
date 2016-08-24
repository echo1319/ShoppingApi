package com.shoppingapp.apis.auth.contollers;

import com.shoppingapp.apis.auth.model.User;
import com.shoppingapp.apis.auth.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        try {
            User user = authenticationService.login(username, password);
            if (user != null) {
                return user;
            }

            throw new Exception("User Does Not Exist");
        } catch (Exception e) {
            throw new Exception("User does note exist" + e);
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User login(@RequestParam("user") User user) throws Exception {

        try {
            authenticationService.register(user);
            throw new Exception("User Does Not Exist");
        } catch (Exception e) {
            throw new Exception("User does note exist" + e);
        }
    }
    

}
