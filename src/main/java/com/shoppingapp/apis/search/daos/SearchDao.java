package com.shoppingapp.apis.search.daos;

import com.shoppingapp.apis.search.model.OrderBy;
import com.shoppingapp.apis.search.model.Product;
import com.shoppingapp.apis.search.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SearchDao {

    public List<Store> getStores(String keyword, OrderBy orderBy);
    public List<Product> getProduct(String keyword);

}
