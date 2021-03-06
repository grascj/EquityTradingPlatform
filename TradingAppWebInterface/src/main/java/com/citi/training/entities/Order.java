package com.citi.training.entities;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Order {
    private boolean buy;
    private double price;
    private int size;
    private String stock;
    private String id;

    public Order() {

    }


    public Order(int size, String stock, double price) {
        this.size = size;
        this.price = price;
        this.stock = stock;
    }

    public Order(boolean buy, double price, int size, String stock) {
        this.buy = buy;
        this.price = price;
        this.size = size;
        this.stock = stock;

    }


    public Order(String buy, String price, String size, String stock, String id) {
        this.buy = Boolean.parseBoolean(buy);
        this.price = Double.parseDouble(price);
        this.size = Integer.parseInt(size);
        this.stock = stock;

        this.id = id;

    }

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getstock() {
        return stock;
    }

    public void setstock(String stock) {
        this.stock = stock;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}



