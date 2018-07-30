package com.citi.training.services;



import com.citi.training.entities.Order;
import com.citi.training.repositories.BrokerMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BrokerMessageService {

    @Autowired
    BrokerMessageRepository brokerUpdateRepository;



    public void writeBrokerMessage(Order message) {
        brokerUpdateRepository.save(message);
    }

}
