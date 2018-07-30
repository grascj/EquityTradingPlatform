package com.citi.training.entities;


import com.citi.training.misc.Action;
import com.citi.training.misc.Trend;

public class BollingerBands extends Strategy {


    private Integer avgSeconds;
    private Integer standardDeviation;


    public BollingerBands(String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer avgSeconds, Integer stanardDeviation) {
        super(ticker, stockQuanity, exitRule, exitPercentage);
        this.avgSeconds = avgSeconds;
        this.standardDeviation = stanardDeviation;

    }

    public void setAvgSeconds(Integer avgSeconds) {
        this.avgSeconds = avgSeconds;
    }

    public void setStandardDeviation(Integer standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
