package com.citi.training.repositories;


import com.citi.training.entities.Order;
import com.citi.training.entities.Trade;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeRepository  extends MongoRepository<Trade, ObjectId> {

}

