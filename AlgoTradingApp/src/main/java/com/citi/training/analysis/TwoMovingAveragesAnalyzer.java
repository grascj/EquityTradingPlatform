package com.citi.training.analysis;

import com.citi.training.entities.*;
import com.citi.training.misc.Action;
import com.citi.training.misc.StockAction;
import com.citi.training.misc.Trend;
import com.citi.training.services.MarketUpdateService;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwoMovingAveragesAnalyzer extends Analyzer {

    @Autowired
    private MarketUpdateService marketUpdateService;

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private TradeService tradeService;

    /**
     * Utilized the data points gven by the two moving average strategy to determine if a stock is currently undervalued or overvalued.
     * It then uses that information to either buy or sell or hold that stock.
     *
     * <p>
     * Data Points from the strategy:
     * 1. Short AvgSeconds: determines how far of an average that the analyzer should use for the short average
     * 2. Long AvgSeconds: determines how far of an average that the analyzer should use for the long average
     *
     * <p>
     * If the the short average is higher or lower than the long average the strat can  determine whether  or not buy or sell a stock:
     * if short average is  higher than the long average
     * the stock is going to trend upwards so buy
     * if short is lower than the high average
     * the stock is going to trend downwards tso sell
     *
     * @param strat the strategy that the analyzer will use to gather information and execute a trade
     * @return
     */
    @Override
    public Order analyze(Strategy strat) {

        Order order = null;
        String ticker = strat.getTicker();
        TwoMovingAverages strategy = (TwoMovingAverages) strat;
        Double longAvg;
        Double shortAvg;

        if (marketUpdateService.movingAverage(ticker, strategy.getLongAverageSeconds()) != null) {
            shortAvg = marketUpdateService.movingAverage(ticker, strategy.getShortAverageSeconds());
            longAvg = marketUpdateService.movingAverage(ticker, strategy.getLongAverageSeconds());
        } else {
            return null;
        }

//        System.out.println("shortAvg: " + shortAvg + " longAvg:" + longAvg );

        Trend newTrend = strategy.getCurrentTrend();


        if (shortAvg > longAvg) {
            newTrend = Trend.UPWARD;
        } else if (shortAvg < longAvg) {
            newTrend = Trend.DOWNWARD;
        }

        if (newTrend != strategy.getCurrentTrend() || strategy.isExit()) {
            MarketUpdate current = marketUpdateService.latestUpdateByTicker(ticker);
            strategy.setCurrentTrend(newTrend);

            /**
             * Exit check
             */
            if (shouldExit(strategy, current.getPrice(), strategy.getExitPercentage(), strategy.getExitRule()) || strategy.isExit()) {

                strategy.setExit(true);
                strategyService.writeStrategy(strat);
                return null;
            }
            System.out.println(current.getPrice());
            order = new Order(strategy.getId(), strategy.getStockQuantity(), ticker, current.getPrice());

            if (newTrend == newTrend.DOWNWARD && strategy.getLookingToBuy() != StockAction.BUY) { //SHORT CROSSED BELOW
                order.setBuy(false);
                strategy.setLookingToBuy(StockAction.BUY);
                strategy.setProfitAndLoss(order.isBuy(), strategy.getStockQuantity(), order.getPrice());
                Trade t = new Trade(order, order.getResult(), strategy.getId().toString(), strategy.getProfitAndLoss());
                tradeService.writeTrade(t);

            } else if (newTrend == Trend.UPWARD && strategy.getLookingToBuy() != StockAction.SELL) { //SHORT CROSSED ABOVE
                order.setBuy(true);
                strategy.setLookingToBuy(StockAction.SELL);
                strategy.setProfitAndLoss(order.isBuy(), strategy.getStockQuantity(), order.getPrice());
                Trade t = new Trade(order, order.getResult(), strategy.getId().toString(), strategy.getProfitAndLoss());
                tradeService.writeTrade(t);

            }
            strategyService.writeStrategy(strat);
        }

        return order;

    }


}
