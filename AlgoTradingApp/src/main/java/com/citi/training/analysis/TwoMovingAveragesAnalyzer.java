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

    @Override
    public Order analyze(Strategy strat) {

        Order order = null;
        String ticker = strat.getTicker();
        TwoMovingAverages strategy = (TwoMovingAverages) strat;

        Double shortAvg = marketUpdateService.movingAverage(ticker, strategy.getShortAverageSeconds());
        Double longAvg = marketUpdateService.movingAverage(ticker, strategy.getLongAverageSeconds());
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
//            if (shouldExit(strategy, current.getPrice(), strategy.getExitPercentage(), strategy.getExitRule()) || strategy.isExit()) {
            if (strategy.isExit() ){
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
