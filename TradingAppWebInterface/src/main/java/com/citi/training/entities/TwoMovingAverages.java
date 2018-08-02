package com.citi.training.entities;


import com.citi.training.misc.Trend;

import java.io.Serializable;


public class TwoMovingAverages extends Strategy implements Serializable {


    private Integer shortAverageSeconds;
    private Integer longAverageSeconds;
    private Trend currentTrend;

    public TwoMovingAverages(String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds, String name) {
        super(ticker, stockQuanity, exitRule, exitPercentage, name);
        this.shortAverageSeconds = shortAverageSeconds;
        this.longAverageSeconds = longAverageSeconds;
        this.currentTrend = Trend.FLAT;
    }

    public TwoMovingAverages() {
        super(null, null, null, null, null);
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
