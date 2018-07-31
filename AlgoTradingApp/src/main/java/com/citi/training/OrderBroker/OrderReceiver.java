package com.citi.training.OrderBroker;

import com.citi.training.entities.Order;
import com.citi.training.entities.Trade;
import com.citi.training.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    TradeService tradeService;

    /**
     * Listens on the OrderBroker_Reply queue for messages from the order broker. Once the method receives an order it converts the order
     * into a trade and sends it to the database
     *
     * @param message Text received from the order broker
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    @JmsListener(destination = "OrderBroker_Reply")
    public void receiveMessage(String message) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        System.out.println("Received transaction");
        tradeService.writeTrade(xmlToBrokerMessage(message));

    }

    /**
     * Converts an order string given by the order broker into a trade that can be sent to the database.
     * The method searches for the first child text in each xml node. The result tag is added to see if the order was completely filled or not
     *
     * @param msg Message string from the order broker
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    public Trade  xmlToBrokerMessage(String msg) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(msg));
        org.w3c.dom.Document doc = builder.parse(is);

        String stock = doc.getElementsByTagName("stock").item(0).getFirstChild().getTextContent();
        String price = doc.getElementsByTagName("price").item(0).getFirstChild().getTextContent();
        String size = doc.getElementsByTagName("size").item(0).getFirstChild().getTextContent();
        String buy = doc.getElementsByTagName("buy").item(0).getFirstChild().getTextContent();
        String id = doc.getElementsByTagName("id").item(0).getFirstChild().getTextContent();
        String result = doc.getElementsByTagName("result").item(0).getFirstChild().getTextContent();

        Order bm = new Order(buy, price, size, stock, id);
        Trade tr = new Trade(bm, result);

        return tr;


    }




}
