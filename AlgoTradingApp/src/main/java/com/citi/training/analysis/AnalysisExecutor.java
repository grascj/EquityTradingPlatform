package com.citi.training.analysis;


import com.citi.training.entities.BollingerBands;
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

    public void execute(Strategy strategy) {
        analyzerMap.get(strategy.getClass()).analyze(strategy);
    }

    @Autowired
    public AnalysisExecutor(BollingerBandsAnalyzer bollingerBandsAnalyzer, TwoMovingAveragesAnalyzer twoMovingAveragesAnalyzer) {
        analyzerMap = new HashMap<>();
        analyzerMap.put(BollingerBands.class, bollingerBandsAnalyzer);
        analyzerMap.put(TwoMovingAverages.class, twoMovingAveragesAnalyzer);
    }
}
