package com.citi.training.services;


import com.citi.training.entities.MarketUpdate;
import com.citi.training.misc.Action;
import com.citi.training.misc.Average;
import com.citi.training.repositories.MarketUpdateRepository;
import com.mongodb.BSONTimestampCodec;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.operation.GroupOperation;
import org.bson.types.BSONTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;


@Service
public class MarketUpdateService {

    @Autowired
    MarketUpdateRepository marketUpdateRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    public void writeMarketUpdates(List<MarketUpdate> tickers) {
        marketUpdateRepository.save(tickers);
    }


    public Double movingAverage(String ticker, int timeInSeconds){
        AggregationResults<Average> res = mongoTemplate.aggregate(
                newAggregation(
                        match(
                                Criteria.where("ticker").is(ticker)
                                .and("timestamp").gte(LocalDateTime.now().minusSeconds(timeInSeconds))
                        ),
                group().avg("price").as("avgPrice")),
                MarketUpdate.class,
                Average.class
        );

        return res.getUniqueMappedResult().getAvgPrice();
    }


    @Scheduled(fixedDelay = 10000)
    public void test(){
        System.out.println(movingAverage("goog", 10));
    }



}
