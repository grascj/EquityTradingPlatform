package com.citi.training.controllers;

import com.citi.training.entities.Trade;
import com.citi.training.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<Trade> allTrades = new ArrayList<>();
        repository.findByResult("FILLED").forEach(allTrades::add);
        return allTrades;
    }

    @GetMapping("/trades/{id}")
    public List<Trade> getTradesByResultAndId(@PathVariable("id") String id) {

        //TODO
        List<Trade> allTrades = new ArrayList<>();
        repository.findAll().forEach(allTrades::add);
        return allTrades;
    }
}
