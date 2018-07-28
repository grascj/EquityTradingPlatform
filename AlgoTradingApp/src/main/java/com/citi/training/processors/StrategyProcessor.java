package com.citi.training.processors;

import com.citi.training.entities.BollingerBands;
import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.misc.MarketInformation;
import com.citi.training.repositories.StrategyRepository;
import com.citi.training.services.MarketUpdateService;
import com.citi.training.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
public class StrategyProcessor {


    @Autowired
    MarketUpdateService marketUpdateService;

    @Autowired
    StrategyService strategyService;

    @Autowired
    MarketInformation marketInformation;


    @Scheduled(fixedDelay = 5000)
    public void executeStrategies(){

        List<Strategy> strat = new LinkedList<>();
        strat.add(new TwoMovingAverages("goog",5,10));
        strat.add(new BollingerBands("apl", 100.1));
        strategyService.writeStrategies(strat);
        

    }













}
