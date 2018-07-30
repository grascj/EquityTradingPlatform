package com.citi.training.entities;

public class Trade {
    Order order;
    String result;

    public Trade(Order order, String result){
        this.order = order;
        this.result  = result;

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
