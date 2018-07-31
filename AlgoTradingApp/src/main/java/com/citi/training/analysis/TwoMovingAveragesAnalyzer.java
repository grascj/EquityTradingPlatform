package com.citi.training.analysis;

import com.citi.training.entities.*;
import com.citi.training.misc.Action;
import com.citi.training.misc.Trend;
import com.citi.training.services.MarketUpdateService;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwoMovingAveragesAnalyzer implements Analyzer {

    @Autowired
    private MarketUpdateService marketUpdateService;

    @Autowired
    private StrategyService strategyService;

    @Autowired
    TradeService tradeService;

    @Override
    public Order analyze(Strategy strat) {

        Order order = null;
        String ticker = strat.getTicker();
        TwoMovingAverages strategy = (TwoMovingAverages) strat;

        Double shortAvg = marketUpdateService.movingAverage(ticker, strategy.getShortAverageSeconds());
        Double longAvg = marketUpdateService.movingAverage(ticker, strategy.getLongAverageSeconds());
        System.out.println("shortAvg: " + shortAvg + " longAvg:" + longAvg);

        Trend newTrend;

        if (shortAvg > longAvg) {
            newTrend = Trend.UPWARD;
        } else if (shortAvg < longAvg) {
            newTrend = Trend.DOWNWARD;
        } else {
            newTrend = strategy.getCurrentTrend();
        }

        if (newTrend != strategy.getCurrentTrend()) {
            MarketUpdate current = marketUpdateService.latestUpdateByTicker(ticker);
            ((TwoMovingAverages) strat).setCurrentTrend(newTrend);


            order = new Order(strat.getStockQuantity(), ticker, current.getPrice());


            if (newTrend == newTrend.DOWNWARD) { //SHORT CROSSED BELOW
                if (!strategy.getLookingToBuy()) {
                    order.setBuy(false);

                    strategy.setLookingToBuy(true);
                    strategy.setProfitAndLoss(false, order.getSize(), order.getPrice());
                    System.out.println(new Trade(order, "Filled", strat.getId().toString(), strategy.getProfitAndLoss()));

                    System.out.println("SELfdaL");

                }

            } else if (newTrend == Trend.UPWARD) { //SHORT CROSSED ABOVE
                if (strategy.getLookingToBuy()) {
                    order.setBuy(true);

                    strategy.setLookingToBuy(false);

                    strategy.setProfitAndLoss(true, order.getSize(), order.getPrice());
                    System.out.println(new Trade(order, "Filled", strat.getId().toString(), strategy.getProfitAndLoss()));
                    System.out.println("BfdaUY");

                }
            }

        }
        strategyService.writeStrategy(strat);
        if(order != null){
            tradeService.writeTrade(new Trade(order, "Filled", strat.getId().toString(), strat.getProfitAndLoss()));
        }

        return order;

    }


}
