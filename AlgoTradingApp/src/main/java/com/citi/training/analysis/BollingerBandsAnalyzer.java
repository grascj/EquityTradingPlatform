package com.citi.training.analysis;

import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.misc.Action;
import org.springframework.stereotype.Component;

@Component
public class BollingerBandsAnalyzer implements Analyzer {

    @Override
    public Order analyze(Strategy strat) {
        return null;
    }
}
