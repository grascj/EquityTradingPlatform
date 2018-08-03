package com.citi.training.analysis;


import com.citi.training.OrderBroker.OrderSender;
import com.citi.training.entities.BollingerBands;
import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AnalysisExecutor {

    private BollingerBandsAnalyzer bollingerBandsAnalyzer;
    private TwoMovingAveragesAnalyzer twoMovingAveragesAnalyzer;
    private Map<Class, Analyzer> analyzerMap;

    /**
     * Takes a strategy from the analyzer map and runes the analyze method on each strategy
     * @param strategy a strategy of a  particular type given by the analyzer map
     * @return
     */
    public Order execute(Strategy strategy) {
        return analyzerMap.get(strategy.getClass()).analyze(strategy);
    }

    @Autowired
    public AnalysisExecutor(BollingerBandsAnalyzer bollingerBandsAnalyzer, TwoMovingAveragesAnalyzer twoMovingAveragesAnalyzer) {
        analyzerMap = new HashMap<>();
        analyzerMap.put(BollingerBands.class, bollingerBandsAnalyzer);
        analyzerMap.put(TwoMovingAverages.class, twoMovingAveragesAnalyzer);
    }
}
