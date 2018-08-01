package com.citi.training.entities;


import com.citi.training.misc.Action;
import com.citi.training.misc.Trend;
import org.springframework.data.mongodb.core.mapping.Document;


public class TwoMovingAverages extends Strategy {


    private Integer shortAverageSeconds;
    private Integer longAverageSeconds;
    private Trend currentTrend;


    public TwoMovingAverages(String ticker, Integer stockQuantity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds) {
        super(ticker, stockQuantity, exitRule, exitPercentage, 0.0);
        this.shortAverageSeconds = shortAverageSeconds;
        this.longAverageSeconds = longAverageSeconds;
        this.currentTrend = Trend.FLAT;

    }


    public Trend getCurrentTrend() {
        return currentTrend;
    }

    public void setCurrentTrend(Trend currentTrend) {
        this.currentTrend = currentTrend;
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


