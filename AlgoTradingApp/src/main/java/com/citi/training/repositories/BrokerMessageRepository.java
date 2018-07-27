package com.citi.training.repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrokerMessageRepository  extends MongoRepository<com.citi.training.entities.BrokerMessage, ObjectId> {

}
