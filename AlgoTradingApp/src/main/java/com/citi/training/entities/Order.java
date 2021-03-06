package com.citi.training.entities;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Order {
    private boolean buy;
    private Double price;
    private Integer size;
    private String stock;

    @Id
    private ObjectId id;

    private ObjectId stratId;


    private String result;
    public Order() {

    }


    public Order(ObjectId stratId, int size, String stock, double price) {
        this.stratId = stratId;
        this.size = size;
        this.price = price;
        this.stock = stock;
        this.id = new ObjectId();
    }

//    public Order(String stratId, boolean buy, double price, int size, String stock) {
//        this.stratId = stratId;
//        this.buy = buy;
//        this.price = price;
//        this.size = size;
//        this.stock = stock;
//        this.id = new ObjectId();
//
//    }


    public Order(String buy, String price, String size, String stockd) {
        this.buy = Boolean.parseBoolean(buy);
        this.price = Double.parseDouble(price);
        this.size = Integer.parseInt(size);
        this.stock = stock;


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


    public String getstock() {
        return stock;
    }

    public void setstock(String stock) {
        this.stock = stock;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getStratId() {
        return stratId;
    }

    public void setStratId(ObjectId stratId) {
        this.stratId = stratId;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {


        return "<trade>\n" +
                "<buy>" + buy + "</buy>\n" +
                "<id>" + "0" + "</id>\n" +
                "<price>" + price + "</price>\n" +
                "<size>" + size + "</size>\n" +
                "<stock>" + stock + "</stock>\n" +
                "<whenAsDate>2014-07-31T22:33:22.801-04:00</whenAsDate>\n" +
                "</trade>";
    }


}



