package com.citi.training.entities;


import com.citi.training.misc.Action;
import org.springframework.data.mongodb.core.mapping.Document;


public class TwoMovingAverages extends Strategy {


    private Integer shortAverageSeconds;
    private Integer longAverageSeconds;

    @Override
    public Action analyzeMarket() {
        System.out.println("moving average: short:"+shortAverageSeconds + " long:"+ longAverageSeconds);
        return Action.HOLD;
    }

    public TwoMovingAverages(String ticker, Integer shortAverageSeconds, Integer longAverageSeconds) {
        super(ticker);
        this.shortAverageSeconds = shortAverageSeconds;
        this.longAverageSeconds = longAverageSeconds;
    }

    public Integer getShortAverageSeconds() {

        return shortAverageSeconds;
    }

    public void setShortAverageSeconds(Integer shortAverageSeconds) {
        this.shortAverageSeconds = shortAverageSeconds;
    }

    public Integer getLongAverageSeconds() {
        return longAverageSeconds;
    }

    public void setLongAverageSeconds(Integer longAverageSeconds) {
        this.longAverageSeconds = longAverageSeconds;
    }
}
