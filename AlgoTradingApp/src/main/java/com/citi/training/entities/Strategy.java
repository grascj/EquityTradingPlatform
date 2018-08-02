package com.citi.training.entities;


import com.citi.training.misc.Action;
import com.citi.training.misc.StockAction;
import jdk.nashorn.internal.runtime.Undefined;
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

    private Double initialValue = 0.0;

    private String name;

    private boolean exit = false;

    private StockAction lookingToBuy = StockAction.UNDEFINED;


    public Strategy(String ticker, Integer stockQuantity, String exitRule, Double exitPercentage, Double profitAndLoss) {
        this.ticker = ticker;
        this.exitRule = exitRule;
        this.exitPercentage = exitPercentage;
        this.stockQuantity = stockQuantity;
        this.profitAndLoss = profitAndLoss;
        this.cashValue = 0.0;
        this.holdingValue = 0.0;
        this.firstTrade = true;
        this.initialValue = 0.0;
        this.name = "";

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


    public void setExitPercentage(Double exitPercentage) {
        this.exitPercentage = exitPercentage;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setProfitAndLoss(Double profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    public boolean isFirstTrade() {
        return firstTrade;
    }

    public void setFirstTrade(boolean firstTrade) {
        this.firstTrade = firstTrade;
    }

    public Double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Double initialValue) {
        this.initialValue = initialValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfitAndLoss(boolean buy, int quantity, double price) {
//        System.out.println("BUY " + buy + "quantity  " + quantity + " price + " + price);
        if (buy) {
            holdingValue = quantity * price;
            if (firstTrade) {
                cashValue = 0.0;
                initialValue = holdingValue;
                firstTrade = false;
            } else {

                cashValue = cashValue - holdingValue;
            }


        } else {
            cashValue = cashValue + quantity * price;
            if (firstTrade) {
                holdingValue = 0.0;
                initialValue = cashValue;
                firstTrade = false;
            } else {

                holdingValue = 0.0;
            }
        }
//        System.out.println("buy " + buy + "cash " + cashValue + "holding " + holdingValue + "total " + this.profitAndLoss);
        profitAndLoss = holdingValue + cashValue;

    }

    public Double getHoldingValue() {
        return holdingValue;
    }

    public void setHoldingValue(Double holdingValue) {
        this.holdingValue = holdingValue;
    }

    public Double getInitialvalue() {
        return initialValue;
    }

    public void setInitialvalue(Double initialvalue) {
        this.initialValue = initialvalue;
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

    public StockAction getLookingToBuy() {
        return lookingToBuy;
    }

    public void setLookingToBuy(StockAction lookingToBuy) {
        this.lookingToBuy = lookingToBuy;
    }


    @Override
    public String toString() {
        return "Strategy{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", exitRule='" + exitRule + '\'' +
                ", exitPercentage=" + exitPercentage +
                ", stockQuantity=" + stockQuantity +
                ", profitAndLoss=" + profitAndLoss +
                ", cashValue=" + cashValue +
                ", holdingValue=" + holdingValue +
                ", firstTrade=" + firstTrade +
                ", initialValue=" + initialValue +
                ", exit=" + exit +
                '}';
    }
}
