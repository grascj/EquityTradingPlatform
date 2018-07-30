package com.citi.training.analysis;

import com.citi.training.entities.BollingerBands;
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
public class BollingerBandsAnalyzer implements Analyzer {

    @Autowired
    private MarketUpdateService marketUpdateService;

    @Autowired
    private StrategyService strategyService;


    @Override
    public Order analyze(Strategy strat) {

        String ticker = strat.getTicker();
        Order order = new Order(100, ticker);


        BollingerBands strategy = (BollingerBands) strat;
        Double movingAvg = marketUpdateService.movingAverage(ticker, strategy.getAvgSeconds());
        Double standardDeviation =marketUpdateService.movingStandardDeviation(ticker, strategy.getAvgSeconds(), strategy.getStandardDeviation());
        Double highStandardDeviation = movingAvg + standardDeviation;
        Double lowStandardDeviation = movingAvg - standardDeviation;

        Double currentPrice = marketUpdateService.latestUpdateByTicker(strategy.getTicker()).getPrice();
        System.out.println("Standard deviation :  " + standardDeviation);
        System.out.println("Standard deviation high:  " + highStandardDeviation);
        System.out.println("Standard deviation low:  " + lowStandardDeviation);
        System.out.println("price:  " + currentPrice);


        Trend newTrend;
        if (currentPrice > highStandardDeviation) {
            order.setPrice(currentPrice);
            newTrend = Trend.DOWNWARD;
        } else if (currentPrice < lowStandardDeviation) {
            order.setPrice(currentPrice);
            newTrend = Trend.UPWARD;
            order.setPrice(currentPrice);
        } else {
            newTrend = strategy.getCurrentTrend();
        }

        if (newTrend != strategy.getCurrentTrend()) {
            ((BollingerBands) strat).setCurrentTrend(newTrend);
            strategyService.writeStrategy(strat);

            if (newTrend == newTrend.DOWNWARD) { //current price went above high SD
                order.setBuy(false);
                System.out.println("SELL");
                return order;
            } else if (newTrend == Trend.UPWARD) { //current price went below low SD
                order.setBuy(true);
                System.out.println("BUY");
                return order;
            }
        }

        return null;
    }
}
