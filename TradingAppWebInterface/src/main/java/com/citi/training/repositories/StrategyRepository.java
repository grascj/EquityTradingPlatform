package com.citi.training.repositories;

import com.citi.training.entities.Strategy;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StrategyRepository  extends MongoRepository<Strategy, ObjectId> {

    Strategy findById(String id);
}
