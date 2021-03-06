package com.citi.training.entities;



import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document
public class MarketUpdate {

    @Indexed
    private LocalDateTime timestamp;
    private String ticker;
    private Double price;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MarketUpdate(LocalDateTime timestamp, String ticker, Double price) {
        this.timestamp = timestamp;
        this.ticker = ticker;
        this.price = price;
    }

    public MarketUpdate() { }





}
