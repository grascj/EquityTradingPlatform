package com.citi.training.services;



import com.citi.training.entities.Order;


import com.citi.training.entities.Trade;
import com.citi.training.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TradeService {

    @Autowired
    TradeRepository tradeUpdateRepository;



    public void writeTrade(Trade trade) {

        tradeUpdateRepository.save(trade);
    }

}
