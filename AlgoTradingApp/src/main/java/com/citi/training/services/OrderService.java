package com.citi.training.services;


import com.citi.training.entities.MarketUpdate;
import com.citi.training.entities.Order;


import com.citi.training.entities.Trade;
import com.citi.training.misc.Calculation;
import com.citi.training.repositories.OrderRepository;
import com.citi.training.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public void writeOrder(Order order) {

        orderRepository.save(order);
    }

    public Order orderOnId(Order order) {


        Query q = new Query();
        q.addCriteria(Criteria.where("id").is(order.getId()));
        Order o = mongoTemplate.findOne(q, Order.class);
        return o;
    }
}
