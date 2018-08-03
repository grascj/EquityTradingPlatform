package com.citi.training.processors;

import com.citi.training.OrderBroker.OrderSender;
import com.citi.training.analysis.AnalysisExecutor;
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
import java.util.Objects;


@Component
public class StrategyProcessor {

    @Autowired
    AnalysisExecutor analysisExecutor;

    @Autowired
    StrategyService strategyService;

    @Autowired
    MarketInformation marketInformation;

    @Autowired
    OrderSender orderSender;


    @Scheduled(fixedDelay = 2500)
    public void executeStrategies(){

        strategyService.getStrategies().stream().forEach(x -> marketInformation.getTickers().add(x.getTicker()));

        strategyService.getStrategies().parallelStream()
                .map(analysisExecutor::execute)
                .filter(Objects::nonNull)
                .forEach(orderSender::send);
}













}
