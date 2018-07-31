package com.citi.training.OrderBroker;

import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


import com.citi.training.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.jms.core.JmsTemplate;

import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;


@Service
public class OrderSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    private Order bm = new Order(true, 10, 10, "GOOG");



    public void sendWrapper() {
        send(bm);
    }


    public void send(Order brokerMsg) {
        System.out.println("Sending a transaction.");
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message msg = session.createTextMessage(brokerMsg.toString());
                System.out.println(brokerMsg.toString());
                msg.setJMSCorrelationID(UUID.randomUUID().toString());
                return msg;
            }
        };
        jmsTemplate.send("OrderBroker", messageCreator);
    }
}