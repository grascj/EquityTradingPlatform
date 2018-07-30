package com.citi.training.controllers;

import com.citi.training.entities.MarketUpdate;
import com.citi.training.repositories.MarketUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MarketUpdateController {
    @Autowired
    private MarketUpdateRepository repository;

    @GetMapping("/updates")
    public List<MarketUpdate> getAllUpdates() {
        List<MarketUpdate> updates = new ArrayList<>();
        repository.findAll().forEach(updates::add);

        return updates;
    }
}
