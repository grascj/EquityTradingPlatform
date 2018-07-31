package com.citi.training.entities;


import com.citi.training.misc.Action;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "strategies")
public abstract class Strategy {

    @Id
    private ObjectId id;
    private String ticker;

    private String exitRule;

    private Double exitPercentage;

    private Integer stockQuantity;

    private Double profitAndLoss;

    private double cashValue;

    private double holdingValue;

    private boolean firstTrade = true;

    private double initialvalue = 0;


    public Strategy(String ticker, Integer stockQuantity, String exitRule, Double exitPercentage, Double profitAndLoss) {
        this.ticker = ticker;
        this.exitRule = exitRule;
        this.exitPercentage = exitPercentage;
        this.stockQuantity = stockQuantity;
        this.profitAndLoss = profitAndLoss;
    }

    public Strategy() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getExitRule() {
        return exitRule;
    }

    public void setExitRule(String exitRule) {
        this.exitRule = exitRule;
    }

    public Double getExitPercentage() {
        return exitPercentage;
    }

    public void setExitPercentage(double exitPercentage) {
        this.exitPercentage = exitPercentage;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(boolean buy, int quantity, double price) {
        if (buy) {
            holdingValue = quantity * price;
            if (firstTrade) {
                cashValue = cashValue - holdingValue;
                firstTrade = false;
            } else {
                cashValue = cashValue - holdingValue;
            }
            if (initialvalue == 0.0) {
                initialvalue = holdingValue;
            }

        } else {
            cashValue = cashValue + quantity * price;
            holdingValue = 0;
            if (initialvalue == 0.0) {
                initialvalue = cashValue;
            }
            this.profitAndLoss = cashValue + holdingValue;


        }
    }
}
