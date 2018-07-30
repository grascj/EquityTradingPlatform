package com.citi.training.analysis;

import com.citi.training.OrderBroker.OrderReceiver;
import com.citi.training.OrderBroker.OrderSender;
import com.citi.training.entities.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderBrokerTest {

    OrderSender os = new OrderSender();
    OrderReceiver or = new OrderReceiver();

    @Test
    public void OrderSendFormatTest() {
        Order order = new Order(true, 10, 12, "GOOG");
        assertEquals(order.toString(), "<trade>\n" +
                "<buy>true</buy>\n" +
                "<id>0</id>\n" +
                "<price>10.0</price>\n" +
                "<size>12</size>\n" +
                "<stock>GOOG</stock>\n" +
                "<whenAsDate>2014-07-31T22:33:22.801-04:00</whenAsDate>\n" +
                "</trade>");
        os.send(order);
    }

    @Test
    public void OrderReceiverFormatTest() {


    }

    @Test
    public void ReceiveAllOrderTest() {
        Order order = new Order(true, 10, 12, "GOOG");
        os.send(order);
    }


}
