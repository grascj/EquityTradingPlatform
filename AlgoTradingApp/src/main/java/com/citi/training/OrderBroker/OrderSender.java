package com.citi.training.OrderBroker;

import java.io.File;
import java.util.UUID;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.jms.core.JmsTemplate;

import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


@Service
@ComponentScan
public class OrderSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 1000)
    public void send() {
        System.out.println("Sending a transaction.");
        // Post message to the message queue named "OrderTransactionQueue"
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message msg = session.createTextMessage("<trade>\n" +
                        "<buy>true</buy>\n" +
                        "<id>0</id>\n" +
                        "<price>88.0</price>\n" +
                        "<size>2000</size>\n" +
                        "<stock>HON</stock>\n" +
                        "<whenAsDate>2014-07-31T22:33:22.801-04:00</whenAsDate>\n" +
                        "</trade>");
                msg.setJMSCorrelationID(UUID.randomUUID().toString());
                return msg;
            }
        };
        jmsTemplate.send("OrderBroker ", messageCreator);
    }
}