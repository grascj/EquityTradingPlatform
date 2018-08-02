package com.citi.training.analysis;

import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.misc.Action;

public abstract class Analyzer {

    abstract Order analyze(Strategy strat);

    public Boolean shouldExit(Strategy strat, Double price, Double exitPercentage, String exitRule) {

        if (strat.getProfitAndLoss() < strat.getInitialvalue() * .8) {
            return true;
        }

        if (exitRule.equals("loss")) {
            if (strat.getProfitAndLoss() < strat.getInitialvalue() * (1 - exitPercentage)) {
                return true;
            }
        }

        if (exitRule.equals("profit")) {
            if (strat.getProfitAndLoss() > strat.getInitialvalue() * (1 + exitPercentage)) {
                return true;
            }
        }


        return false;
    }


}
