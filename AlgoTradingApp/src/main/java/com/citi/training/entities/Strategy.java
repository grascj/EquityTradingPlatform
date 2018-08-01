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

    private Double cashValue;

    private Double holdingValue;

    private boolean firstTrade = true;

    private Double initialvalue = 0.0;


    private boolean exit = false;


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
                cashValue = 0.0;
                initialvalue = holdingValue;
                firstTrade = false;
            } else {
                cashValue = cashValue - holdingValue;
            }


        } else {
            cashValue = cashValue + quantity * price;
            if (firstTrade) {
                holdingValue = 0.0;
                initialvalue = cashValue;
                firstTrade = false;
            } else {

                holdingValue = 0.0;
            }
        }
        this.profitAndLoss = cashValue + holdingValue;
    }

    public Double getHoldingValue() {
        return holdingValue;
    }

    public void setHoldingValue(Double holdingValue) {
        this.holdingValue = holdingValue;
    }

    public Double getInitialvalue() {
        return initialvalue;
    }

    public void setInitialvalue(Double initialvalue) {
        this.initialvalue = initialvalue;
    }

    public Double getCashValue() {
        return cashValue;
    }

    public void setCashValue(Double cashValue) {
        this.cashValue = cashValue;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }


}
