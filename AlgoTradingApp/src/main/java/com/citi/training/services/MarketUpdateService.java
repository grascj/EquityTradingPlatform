package com.citi.training.services;


import com.citi.training.entities.MarketUpdate;
import com.citi.training.misc.Calculation;
import com.citi.training.repositories.MarketUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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


    public Double movingAverage(String ticker, int timeInSeconds) {


        AggregationResults<Calculation> res = mongoTemplate.aggregate(
                newAggregation(
                        match(
                                Criteria.where("ticker").is(ticker)
                                        .and("timestamp").gte(LocalDateTime.now().minusSeconds(timeInSeconds))
                        ),
                        group().avg("price").as("result")),
                MarketUpdate.class,
                Calculation.class
        );

        return res.getUniqueMappedResult() == null ? null : res.getUniqueMappedResult().getResult();
    }

    public Double movingStandardDeviation(String ticker, int timeInSeconds, Double standardDeviation) {

        AggregationResults<Calculation> res = mongoTemplate.aggregate(
                newAggregation(
                        match(
                                Criteria.where("ticker").is(ticker)
                                        .and("timestamp").gte(LocalDateTime.now().minusSeconds(timeInSeconds))
                        ),
                        group().stdDevPop("price").as("result")),
                MarketUpdate.class,
                Calculation.class
        );

        return res.getUniqueMappedResult() == null ? null : res.getUniqueMappedResult().getResult() * standardDeviation;
    }


    public MarketUpdate latestUpdateByTicker(String ticker) {
        return marketUpdateRepository.findFirstByTickerOrderByTimestampDesc(ticker);
    }
}
