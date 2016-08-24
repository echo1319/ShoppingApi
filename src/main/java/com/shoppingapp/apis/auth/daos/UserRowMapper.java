package com.shoppingapp.apis.auth.daos;


import com.shoppingapp.apis.auth.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();

        user.setUsername(rs.getString(1));
        user.setPassword(rs.getString(2));
        user.setName(rs.getString(3));
        user.setSurname(rs.getString(4));
        user.setEmail(rs.getString(5));
        user.setBirthdate(rs.getString(6));

        return user;
    }

/*

  username text NOT NULL,
  password text NOT NULL,
  name text,
  surname text,
  mail text NOT NULL,
  birthdate text
 */

}
