package com.citi.training.analysis;

import com.citi.training.entities.MarketUpdate;
import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.misc.Action;
import com.citi.training.misc.Trend;
import com.citi.training.services.MarketUpdateService;
import com.citi.training.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwoMovingAveragesAnalyzer implements Analyzer {

    @Autowired
    private MarketUpdateService marketUpdateService;

    @Autowired
    private StrategyService strategyService;


    @Override
    public Order analyze(Strategy strat) {

        Order order = null;
        String ticker = strat.getTicker();
        TwoMovingAverages strategy = (TwoMovingAverages) strat;

        Double shortAvg = marketUpdateService.movingAverage(ticker, strategy.getShortAverageSeconds());
        Double longAvg = marketUpdateService.movingAverage(ticker, strategy.getLongAverageSeconds());
        System.out.println("shortAvg: " + shortAvg + " longAvg:" + longAvg);
        System.out.println(strategy.getId());
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
            System.out.println(current.getTicker() + " CURRENT PRICE IS: " + current.getPrice());

            if (newTrend == newTrend.DOWNWARD) { //SHORT CROSSED BELOW
                if (!strategy.getLookingToBuy()) {
                    order.setBuy(false);
                    order.setId(strategy.getId());
                    strategy.setLookingToBuy(true);
                    strategy.setProfitAndLoss(false, order.getSize(), order.getPrice());
                    System.out.println("SELL");
                }

            } else if (newTrend == Trend.UPWARD) { //SHORT CROSSED ABOVE
                if (strategy.getLookingToBuy()) {
                    order.setBuy(true);
                    order.setId(strategy.getId());
                    strategy.setLookingToBuy(false);
                    strategy.setProfitAndLoss(true, order.getSize(), order.getPrice());
                    System.out.println("BUY");
                }
            }

        }
        strategyService.writeStrategy(strat);
        return order;

    }


}
