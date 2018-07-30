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


@Component
public class StrategyProcessor {

    @Autowired
    AnalysisExecutor analysisExecutor;

    @Autowired
    StrategyService strategyService;

    @Autowired
    OrderSender orderSender;


    @Scheduled(fixedDelay = 5000)
    public void executeStrategies(){

//        List<Strategy> li = new LinkedList<>();
//        li.add(new BollingerBands("goog", 1.1));
//        li.add(new TwoMovingAverages("aapl", 10, 30));
//        strategyService.writeStrategies(li);
//        strategyService.getStrategies().parallelStream().forEach(analysisExecutor::execute);

        strategyService.getStrategies().parallelStream().map(analysisExecutor::execute).forEach(orderSender::send);
    }













}
