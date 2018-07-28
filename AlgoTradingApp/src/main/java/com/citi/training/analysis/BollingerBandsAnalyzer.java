package com.citi.training.analysis;

import com.citi.training.entities.Strategy;
import com.citi.training.misc.Action;
import org.springframework.stereotype.Component;

@Component
public class BollingerBandsAnalyzer implements Analyzer {

    @Override
    public Action analyze(Strategy strat) {
        System.out.println("bollinger");
        return null;
    }
}
