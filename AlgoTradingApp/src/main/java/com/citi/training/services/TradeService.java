package com.citi.training.services;


import com.citi.training.entities.Order;


import com.citi.training.entities.Trade;
import com.citi.training.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class TradeService {

    @Autowired
    TradeRepository tradeUpdateRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public Trade tradeOnId(Trade trade) {


        Query q = new Query();
        q.addCriteria(Criteria.where("id").is(trade.getId()));
        Trade t = mongoTemplate.findOne(q, Trade.class);
        return t;
    }

    public void writeTrade(Trade trade) {

        tradeUpdateRepository.save(trade);
    }

}
