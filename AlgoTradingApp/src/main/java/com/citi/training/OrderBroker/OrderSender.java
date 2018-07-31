package com.citi.training.OrderBroker;

import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import com.citi.training.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;


@Service
public class OrderSender {

    @Autowired
    private JmsTemplate jmsTemplate;



    /**
     * Test method to see if the broker message receives messages.
     *
     */
    public void sendWrapper() {
        Order bm = new Order(true, 10, 10, "GOOG");
        send(bm);
    }

    /**
     * Sends a specific order to the order broker. The method recives the message from the analysis executor.
     * @param brokerMsg
     */
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