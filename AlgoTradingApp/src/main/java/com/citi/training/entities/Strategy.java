package com.citi.training.entities;


import com.citi.training.misc.Action;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="strategies")
public abstract class Strategy {

    @Id
    private ObjectId id;

    private String ticker;

    public Strategy(String ticker) {
        this.ticker = ticker;
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
}
