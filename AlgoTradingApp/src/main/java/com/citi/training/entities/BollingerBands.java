package com.citi.training.entities;


import com.citi.training.misc.Action;

public class BollingerBands extends Strategy {

    Double test;

    public BollingerBands(String ticker, Double test) {
        super(ticker);
        this.test = test;
    }

}
