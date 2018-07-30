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


        return null;
    }
}
