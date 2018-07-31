package com.citi.training.controllers;

import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.repositories.StrategyRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StrategyController {

    @Autowired
    StrategyRepository repository;

    @GetMapping("/strats")
    public List<Strategy> getAllUpdates() {
        List<Strategy> allStrats = new ArrayList<>();
        repository.findAll().forEach(allStrats::add);
        return allStrats;
    }

    @PostMapping("/addStrat")
    public Strategy postStrategy(@RequestBody Strategy strat) {

        return repository.save(strat);

    }

    @DeleteMapping("/deleteStrat/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(repository.findById(id));
    }



}
