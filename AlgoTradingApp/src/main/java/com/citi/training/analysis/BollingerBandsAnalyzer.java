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
public class BollingerBandsAnalyzer extends Analyzer {

    @Autowired
    private MarketUpdateService marketUpdateService;

    @Autowired
    private StrategyService strategyService;

//    @Autowired
//    private TradeService tradeService;

    /**
     * Utilized the data points given by the bollinger bands strategy to determine if a stock is currently undervalued or overvalued.
     * It then uses that information to either buy sell or hold that stock.
     * <p>
     * Data Points from the strategy:
     * 1. AvgSeconds: determines how far of an average that the analyzer should use.
     * 2. Standard Deviation: measures how many s'ds away from the mean the analyzer should look for
     * 3. movingAvg : the moving average based on the avgSeconds that the user inputs
     * <p>
     * If the moving average is higher or lower than  the number of standard deviations from the mean the analyzer can determine if the stock is overvalue dor undervalued;
     * if higher than the sd's
     * than the stock is  overvalued, so sell
     * if lower than the sd's
     * then the stock is undervalued , so buy
     *
     * @param strat the strategy that the analyzer will use to gather information and execute a trade
     * @return
     */
    @Override
    public Order analyze(Strategy strat) {

        String ticker = strat.getTicker();
        Order order = null;// = new Order(100, ticker, 100.0);

        BollingerBands strategy = (BollingerBands) strat;

        Double movingAvg = marketUpdateService.movingAverage(ticker, strategy.getAvgSeconds());
        Double standardDeviation = marketUpdateService.movingStandardDeviation(ticker, strategy.getAvgSeconds(), strategy.getStandardDeviation());
        Double highStandardDeviation = movingAvg + standardDeviation * strategy.getStandardDeviation();
        Double lowStandardDeviation = movingAvg - standardDeviation * strategy.getStandardDeviation();


        Double currentPrice = marketUpdateService.latestUpdateByTicker(strategy.getTicker()).getPrice();
//        System.out.println("low :  " + lowStandardDeviation + " high: " + highStandardDeviation + " prie: " + currentPrice);
        if (shouldExit(strategy, currentPrice) || strategy.isExit()) {
            strategy.setExit(true);
            strategyService.writeStrategy(strat);
            return null;
        }


        order = new Order(strategy.getId().toString(), strategy.getStockQuantity(), ticker, currentPrice);
        if (currentPrice > highStandardDeviation && !strategy.isLookingTobuy()) { //sell
            order.setBuy(false);
            strategy.setLookingTobuy(true);
            strategyService.writeStrategy(strategy);

//            strategy.setProfitAndLoss(false, order.getSize(), order.getPrice());
//            tradeService.writeTrade(new Trade(order, "Filled", strategy.getId().toString(), strategy.getProfitAndLoss()));
        } else if (currentPrice < lowStandardDeviation && strategy.isLookingTobuy()) { //buy
            order.setBuy(true);
            strategy.setLookingTobuy(false);
            strategyService.writeStrategy(strategy);
//            strategy.setProfitAndLoss(true, order.getSize(), order.getPrice());
//            tradeService.writeTrade(new Trade(order, "Filled", strategy.getId().toString(), strategy.getProfitAndLoss()));
        }

        return order;
        /*
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


            if (newTrend == newTrend.DOWNWARD) { //current price went above high SD
                if (!strategy.isLookingTobuy()) {
                    order.setBuy(false);
                    strategy.setLookingTobuy(false);
                    strategy.setProfitAndLoss(true, order.getSize(), order.getPrice());
                    System.out.println("SELL");
                    return order;
                }

            } else if (newTrend == Trend.UPWARD) { //current price went below low SD
                if (strategy.isLookingTobuy()) {
                    order.setBuy(true);

                    strategy.setLookingTobuy(false);
                    strategy.setProfitAndLoss(true, order.getSize(), order.getPrice());
                    System.out.println("BUY");
                    return order;
                }

            }
        }
        strategyService.writeStrategy(strat);

        tradeService.writeTrade(new Trade(order, "Filled", strat.getId().toString(), strat.getProfitAndLoss()));

        return null;
        */
    }


}
