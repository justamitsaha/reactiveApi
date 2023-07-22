package com.saha.amit.entity;

import org.springframework.data.annotation.Id;

public class Products {
    @Id
    private String id;
    private String description;
    private Integer price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
