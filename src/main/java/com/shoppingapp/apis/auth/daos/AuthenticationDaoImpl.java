package com.shoppingapp.apis.auth.daos;

import com.shoppingapp.apis.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthenticationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User login(String username, String password) {

        String sql = "Select * from users where username =? and password=?";

        User user = (User) jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new UserRowMapper());

        if (user.getUsername() != null) {
            return user;
        }

        return null;
    }


    @Override
    public void register(User user) throws Exception {

        String sql = "Inset into users values ( ?,?,?,?,?,?);";

        int returnValue = jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), user.getBirthdate()});
        if (returnValue < 0) {
            throw new Exception("User Registration Could Not Be Completed");

        }
    }

}
