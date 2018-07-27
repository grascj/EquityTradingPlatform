package com.citi.training.OrderBroker;

import com.citi.training.entities.BrokerMessage;
import com.citi.training.services.BrokerMessageService;
import com.citi.training.services.MarketUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;



@Component
public class OrderReceiver {


    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;
    @Autowired
    BrokerMessageService brokerMessageService;
    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
    @JmsListener(destination = "OrderBroker_Reply", containerFactory = "myFactory")
    public void receiveMessage(String message) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        System.out.println("Received transaction");
        xmlToBrokerMessage(message);
        brokerMessageService.writeBrokerMessage(xmlToBrokerMessage(message));

    }


    public BrokerMessage xmlToBrokerMessage(String msg) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(msg));
        org.w3c.dom.Document doc = builder.parse(is);

        String stock  = doc.getElementsByTagName("stock").item(0).getFirstChild().getTextContent();
        String price  = doc.getElementsByTagName("price").item(0).getFirstChild().getTextContent();
        String size  = doc.getElementsByTagName("size").item(0).getFirstChild().getTextContent();
        String buy  = doc.getElementsByTagName("buy").item(0).getFirstChild().getTextContent();

        BrokerMessage bm =  new BrokerMessage(buy, price, size, stock);

        return bm;


    }
}
