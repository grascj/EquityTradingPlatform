package com.citi.training.OrderBroker;

import com.citi.training.entities.Order;
import com.citi.training.entities.Strategy;
import com.citi.training.entities.Trade;
import com.citi.training.services.OrderService;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TradeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;


@Component
public class OrderReceiver {


    @Autowired
    TradeService tradeService;

    @Autowired
    OrderService orderService;

    @Autowired
    StrategyService strategyService;

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
    public void receiveMessage(Message message) throws Exception {


//        Order o = xmlToBrokerMessage(((TextMessage) message).getText());

//        Strategy s = strategyService.stratOnId(new ObjectId(message.getJMSCorrelationID()));
//        System.out.println("Received transaction from "  + s.getName() + " buy " + o.getPrice() + " price:   " + o.isBuy());
//        s.setProfitAndLoss(o.isBuy(), s.getStockQuantity(), o.getPrice());
//        Trade t = new Trade(o, o.getResult(), message.getJMSCorrelationID(), s.getProfitAndLoss());
//        tradeService.writeTrade(t);
//        strategyService.writeStrategy(s);
//        System.out.println(s.getLookingtoBuy);
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
    public Order xmlToBrokerMessage(String msg) throws Exception {
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

        Order bm = new Order(buy, price, size, stock);
        bm.setResult(result);

        return bm;


    }

    public static final void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
//        System.out.println(out.toString());
    }


}
