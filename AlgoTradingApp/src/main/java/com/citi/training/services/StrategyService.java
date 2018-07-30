package com.citi.training.services;


import com.citi.training.entities.*;
import com.citi.training.repositories.TradeRepository;

import com.citi.training.repositories.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StrategyService {

    @Autowired
    StrategyRepository strategyRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public void writeStrategies(List<Strategy> strategies) {
        strategyRepository.save(strategies);
    }

    public List<Strategy> getStrategies() {
        return strategyRepository.findAll();
    }


    public void writeStrategy(Strategy strategy) { strategyRepository.save(strategy); }

}
