package com.citi.training.services;



import com.citi.training.entities.BrokerMessage;
import com.citi.training.entities.MarketUpdate;
import com.citi.training.repositories.BrokerMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class BrokerMessageService {

    @Autowired
    BrokerMessageRepository brokerUpdateRepository;



    public void writeBrokerMessage(BrokerMessage message) {
        brokerUpdateRepository.save(message);
    }

}
