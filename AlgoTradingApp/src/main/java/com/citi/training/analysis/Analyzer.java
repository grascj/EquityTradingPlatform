package com.citi.training.analysis;

import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.misc.Action;

public abstract  class Analyzer {

    abstract Order analyze(Strategy strat);

    public Boolean shouldExit(Strategy strat, Double price) {
        Integer quantity = strat.getStockQuantity();
        if (strat.getHoldingValue() != 0.0) {
            Double portfolioValue = quantity * price + strat.getCashValue();
            System.out.println("port value: " + portfolioValue + "    intitialvalue:   " + strat.getInitialvalue());
            if (portfolioValue < strat.getInitialvalue() * 0.8) {
                return true;
            }
        }


        return false;
    }


}
