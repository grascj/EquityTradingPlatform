package com.citi.training.analysis;


import com.citi.training.entities.BollingerBands;
import com.citi.training.entities.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AnalysisExecutor {

    @Autowired
    BollingerBandsAnalyzer bollingerBandsAnalyzer;

    @Autowired
    TwoMovingAverageAnalyzer twoMovingAverageAnalyzer;


    Map<Class, Analyzer> analyzerMap;




    public void execute(Strategy strategy) {
        analyzerMap.get(strategy.getClass()).analyze(strategy);
    }
    

    public AnalysisExecutor() {
        analyzerMap = new HashMap<>();
        analyzerMap.put(BollingerBands.class,bollingerBandsAnalyzer);
        analyzerMap.put(TwoMovingAverageAnalyzer.class,twoMovingAverageAnalyzer);
    }
}
