package com.shoppingapp.apis.search.daos;


import com.shoppingapp.apis.search.model.Store;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRowMapper implements RowMapper {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Store store = new Store();
        store.setStoreId(rs.getString(1));
        store.setName(rs.getString(2));
        store.setAddress(rs.getString(3));
        store.setPrice(Double.parseDouble(rs.getString(4)));
        return store;
    }
/*
    s.store_id ,s.name ,s.address ,s.country
*/

}
