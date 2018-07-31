package com.citi.training.entities;


import com.citi.training.OrderBroker.OrderSender;

import org.xml.sax.InputSource;
import org.springframework.data.mongodb.core.mapping.Document;
import org.xml.sax.SAXException;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@Document
public class Order {
    private boolean buy;
    private double price;
    private int size;
    private String stock;

    public Order() {

    }


    public Order(int size, String stock) {
        this.size = size;
        this.stock = stock;
    }

    public Order(boolean buy, double price, int size, String stock) {
        this.buy = buy;
        this.price = price;
        this.size = size;
        this.stock = stock;

    }


    public Order(String buy, String price, String size, String stock) {
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


    @Override
    public String toString() {


        return "<trade>\n" +
                "<buy>"+buy+"</buy>\n" +
                "<id>0</id>\n" +
                "<price>"+price+"</price>\n" +
                "<size>"+size+"</size>\n" +
                "<stock>"+stock+"</stock>\n" +
                "<whenAsDate>2014-07-31T22:33:22.801-04:00</whenAsDate>\n" +
                "</trade>";
    }


}



