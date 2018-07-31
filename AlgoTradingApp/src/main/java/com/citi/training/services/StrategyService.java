package com.citi.training.services;


import com.citi.training.entities.*;
import com.citi.training.repositories.TradeRepository;

import com.citi.training.repositories.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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


    public void writeStrategy(Strategy strategy) {

        strategyRepository.save(strategy);
    }

//    @Scheduled(fixedDelay = 1000)
//    public void addStrategyToLocalDb() {
//        System.out.println("here");
//        writeStrategy(new TwoMovingAverages(    "GOOG", 100, "", 5.0, 2, 10));
//    }
}
