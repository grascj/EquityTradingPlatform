package com.citi.training.entities;


import com.citi.training.misc.Action;
import com.citi.training.misc.Trend;

public class BollingerBands extends Strategy {


    private Integer avgSeconds;
    private Double standardDeviation;



    public BollingerBands(String ticker, Integer stockQuantity, String exitRule, Double exitPercentage, Integer avgSeconds, Double standardDeviation) {
        super(ticker, stockQuantity, exitRule, exitPercentage, 0.0);
        this.avgSeconds = avgSeconds;
        this.standardDeviation = standardDeviation;


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






}
