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

        Trend newTrend;

        if (shortAvg > longAvg) {
            newTrend = Trend.UPWARD;
        } else if (shortAvg < longAvg) {
            newTrend = Trend.DOWNWARD;
        } else {
            newTrend = strategy.getCurrentTrend();
        }

        if (newTrend != strategy.getCurrentTrend()) {
            ((TwoMovingAverages) strat).setCurrentTrend(newTrend);
            strategyService.writeStrategy(strat);
            order = new Order(strat.getStockQuantity(), ticker);

            MarketUpdate current = marketUpdateService.latestUpdateByTicker(ticker);
            System.out.println(current.getTicker() + " CURRENT PRICE IS: " + current.getPrice());
            order.setPrice(current.getPrice());

            if (newTrend == newTrend.DOWNWARD) { //SHORT CROSSED BELOW
                order.setBuy(false);
                System.out.println("SELL");
            } else if (newTrend == Trend.UPWARD) { //SHORT CROSSED ABOVE
                order.setBuy(true);
                System.out.println("BUY");
            }

        }
        return order;

    }
}
