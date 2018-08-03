package com.citi.training.analysis;

import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.misc.Action;

public abstract class Analyzer {

    /**
     * An abstract method that executes the logic in each strategy
     * @param strat
     * @return
     */
    abstract Order analyze(Strategy strat);

    /**
     * A strategy that checks to see if a strategy has met it's exit condition.
     * Default condition is a 20% loss
     * @param strat the strat that the exit condition is checking for
     * @param price a price variable that can be checked on live prices (not yet implemented )
     * @param exitPercentage the percentage that the strategy should exit at
     * @param exitRule whether or no the strat should exit at a profit or loss
     * @return a boolean that says whether or not the strat should exit
     */
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
