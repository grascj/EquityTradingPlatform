package com.citi.training.services;


import com.citi.training.entities.BrokerMessage;
import com.citi.training.entities.MarketUpdate;
import com.citi.training.entities.Strategy;
import com.citi.training.repositories.BrokerMessageRepository;

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


}
