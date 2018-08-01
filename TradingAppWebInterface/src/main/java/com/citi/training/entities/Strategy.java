package com.citi.training.entities;

import com.citi.training.deserializers.StrategyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonDeserialize(using = StrategyDeserializer.class)
@Document(collection="strategies")
public abstract class Strategy {

    @Id
    private ObjectId id;

    private String ticker;

    private String exitRule;

    private Double exitPercentage;

    private Integer stockQuantity;

    private Double cashValue;

    private Double holdingValue;

    private Boolean firstTrade = true;

    private Double initialvalue = 0.0;

    private Boolean exit = false;

    private Boolean lookingToBuy = true;

    private String name;


    public Strategy(String ticker, Integer stockQuantity, String exitRule, Double exitPercentage, String name) {
        this.ticker = ticker;
        this.exitRule = exitRule;
        this.exitPercentage = exitPercentage;
        this.stockQuantity = stockQuantity;
        this.cashValue = 0.0;
        this.holdingValue = 0.0;
    }

    public String getName() {  return name;   }

    public void setName(String name) {  this.name = name;  }

    public String getId() { return id.toHexString();  }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getExitRule() {  return exitRule;     }

    public void setExitRule(String exitRule) { this.exitRule = exitRule;   }

    public Double getExitPercentage() { return exitPercentage;  }

    public void setExitPercentage(double exitPercentage) {  this.exitPercentage = exitPercentage;  }

    public Integer getStockQuantity() { return stockQuantity;   }

    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public Double getCashValue() { return cashValue;  }

    public void setCashValue(Double cashValue) { this.cashValue = cashValue;   }

    public Double getHoldingValue() {  return holdingValue;  }

    public void setHoldingValue(Double holdingValue) {  this.holdingValue = holdingValue;  }

    public Boolean getFirstTrade() {  return firstTrade;   }

    public void setFirstTrade(Boolean firstTrade) {  this.firstTrade = firstTrade;  }

    public Double getInitialvalue() { return initialvalue;  }

    public void setInitialvalue(Double initialvalue) { this.initialvalue = initialvalue;    }

    public Boolean getExit() {  return exit;  }

    public void setExit(Boolean exit) { this.exit = exit; }

    public Boolean getLookingToBuy() {   return lookingToBuy;  }

    public void setLookingToBuy(Boolean lookingToBuy) {   this.lookingToBuy = lookingToBuy;  }

}
