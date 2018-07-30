package com.citi.training.analysis;

import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.misc.Action;

public interface Analyzer {

    Order analyze(Strategy strat);


}
