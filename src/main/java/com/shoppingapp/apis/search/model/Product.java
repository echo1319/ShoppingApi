/**
 * Created by eri_k on 8/22/2016.
 */
package com.shoppingapp.apis.search.model;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {
    private String productId;
    private String name;
    private String category;
    private String description;

    public Product() {
        this.productId = UUID.randomUUID().toString();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
