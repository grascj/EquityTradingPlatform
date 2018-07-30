package com.citi.training.controllers;

import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.repositories.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StrategyController {

    @Autowired
    StrategyRepository repository;

    @PostMapping("/addStrat")
    public Strategy postCustomer(@RequestBody Strategy strat) {

        TwoMovingAverages strategy = (TwoMovingAverages) strat;

        return repository.save(strategy);

    }



}
