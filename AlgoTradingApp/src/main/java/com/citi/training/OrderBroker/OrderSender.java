package com.citi.training.OrderBroker;

import java.io.File;
import java.util.UUID;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


import com.citi.training.entities.BrokerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.jms.core.JmsTemplate;

import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@ComponentScan
public class OrderSender {

    @Autowired
    private JmsTemplate jmsTemplate;
    private BrokerMessage bm = new BrokerMessage(true, 10, 10, "GOOG");



    public void sendWrapper() {
        send(bm);
    }


    public void send(BrokerMessage brokerMsg) {
        System.out.println("Sending a transaction.");
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message msg = session.createTextMessage(brokerMsg.toString());
                msg.setJMSCorrelationID(UUID.randomUUID().toString());
                return msg;
            }
        };
        jmsTemplate.send("OrderBroker", messageCreator);
    }
}