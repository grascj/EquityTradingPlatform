package com.citi.training.controllers;

import com.citi.training.entities.Trade;
import com.citi.training.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/performance")
@CrossOrigin("*")
public class TradeController {

    @Autowired
    TradeRepository repository;

    @GetMapping("/trades")
    public List<Trade> getTradesByResult() {
        List<Trade> allTrades = new ArrayList<Trade>();
        repository.findByResult("Filled").forEach(allTrades::add);
        return allTrades;
    }

    @GetMapping("/trades/{id}")
    public List<Trade> getTradesByResultAndId(@PathVariable("id") String id) {
        List<Trade> allTrades = new ArrayList<>();
        repository.findByStrategyIdAndResult(id, "Filled").forEach(allTrades::add);
        return allTrades;
    }

    @GetMapping("/recentTrades/{id}")
    public List<Trade> getTradesOfPastHr(@PathVariable("id") String id) {
        List<Trade> allTrades = new ArrayList<>();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime anHrAgo = currentTime.minusHours(1);
        //System.out.println(currentTime.toString() + " and an hour ago: " + anHrAgo.toString());
        repository.findByIdAndResultAndTimeStampBetween(id, "Filled", anHrAgo, currentTime).forEach(allTrades::add);
        //System.out.println("SIZE " + allTrades.size());
        return allTrades;
    }
}
