package com.citi.training.OrderBroker;

import com.citi.training.entities.Order;
import com.citi.training.entities.Trade;
import com.citi.training.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.IOException;
import java.io.StringReader;


@Component
public class OrderReceiver {

    int orderCounter = 0;
    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;
    @Autowired
    TradeService tradeService;

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
    @JmsListener(destination = "OrderBroker_Reply", containerFactory = "myFactory")
    public void receiveMessage(String message) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        System.out.println("Received transaction");
        System.out.println(message);
        orderCounter++;
        tradeService.writeTrade(xmlToBrokerMessage(message));

    }


    public Trade  xmlToBrokerMessage(String msg) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(msg));
        org.w3c.dom.Document doc = builder.parse(is);

        String stock = doc.getElementsByTagName("stock").item(0).getFirstChild().getTextContent();
        String price = doc.getElementsByTagName("price").item(0).getFirstChild().getTextContent();
        String size = doc.getElementsByTagName("size").item(0).getFirstChild().getTextContent();
        String buy = doc.getElementsByTagName("buy").item(0).getFirstChild().getTextContent();
        String result = doc.getElementsByTagName("result").item(0).getFirstChild().getTextContent();

        Order bm = new Order(buy, price, size, stock);
        Trade tr = new Trade(bm, result);

        return tr;


    }

    public int getOrderCounter() {
        return orderCounter;
    }


}
