package com.citi.training.repositories;


import com.citi.training.entities.MarketUpdate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketUpdateRepository extends MongoRepository<com.citi.training.entities.MarketUpdate, ObjectId> {
    MarketUpdate findFirstByTickerOrderByTimestampDesc(String Ticker);
}
