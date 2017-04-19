package com.szkingdom.ssm.entity;

/**
 * Created by phoenix on 2017/3/30.
 */
public class Item {

    private Long id;
    private String product;
    private double price;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
