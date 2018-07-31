package com.citi.training.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "trades")

public class Trade {
    @Id
    private ObjectId id;
    Order order;
    String result;
    String strategyId;
    Double profitAndLoss;
    LocalDateTime timeStamp;

    public Trade(Order order, String result) {
        this.order = order;
        this.result = result;
        this.timeStamp = LocalDateTime.now();

    }

    public Trade(Order order, String filled, String strategyId, Double profitAndLoss) {
        this.order = order;
        this.result = filled;
        this.strategyId = strategyId;
        this.profitAndLoss = profitAndLoss;
        this.timeStamp = LocalDateTime.now();
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

    public String getId() {
        return strategyId;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Double getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(Double profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }


    @Override
    public String toString() {
        return "Trade{" +
                "order=" + order +
                ", result='" + result + '\'' +
                ", id='" + id + '\'' +
                ", profitAndLoss=" + profitAndLoss +
                '}';
    }
}
