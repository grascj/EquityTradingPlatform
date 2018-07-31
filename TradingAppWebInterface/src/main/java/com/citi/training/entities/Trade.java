package com.citi.training.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "trades")

public class Trade {
    @Id
    private ObjectId id;
    private Order order;
    private String result;
    private String strategyId;
    private Double profitAndLoss;
    private LocalDateTime timeStamp;

    public Trade(Order order, String result, String strategyId, Double profitAndLoss) {
        this.order = order;
        this.result = result;
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

    public String getTimeStamp() { return timeStamp.toString();   }

    public void setTimeStamp(LocalDateTime timeStamp) { this.timeStamp = timeStamp; }


}
