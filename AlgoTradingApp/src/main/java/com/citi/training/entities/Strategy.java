package com.citi.training.entities;


import com.citi.training.misc.Action;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="strategies")
public abstract class Strategy {
    private String ticker;

    public abstract Action analyzeMarket();

    public Strategy(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
