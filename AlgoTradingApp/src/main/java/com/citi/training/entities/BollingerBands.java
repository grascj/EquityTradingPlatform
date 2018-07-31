package com.citi.training.entities;


import com.citi.training.misc.Action;
import com.citi.training.misc.Trend;

public class BollingerBands extends Strategy {


    private Integer avgSeconds;
    private Double standardDeviation;
    private Trend currentTrend;
    private boolean lookingToBuy;


    public BollingerBands(String ticker, Integer stockQuantity, String exitRule, Double exitPercentage, Integer avgSeconds, Double standardDeviation) {
        super(ticker, stockQuantity, exitRule, exitPercentage, 0.0);
        this.avgSeconds = avgSeconds;
        this.standardDeviation = standardDeviation;
        this.currentTrend = currentTrend.FLAT;
        this.lookingToBuy = true;

    }

    public void setAvgSeconds(Integer avgSeconds) {
        this.avgSeconds = avgSeconds;
    }

    public void setStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Integer getAvgSeconds() {
        return avgSeconds;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public Trend getCurrentTrend() {
        return currentTrend;
    }

    public void setCurrentTrend(Trend currentTrend) {
        this.currentTrend = currentTrend;
    }

    public boolean isLookingTobuy() {
        return lookingToBuy;
    }

    public void setLookingTobuy(boolean lookingTobuy) {
        this.lookingToBuy = lookingTobuy;
    }


}
