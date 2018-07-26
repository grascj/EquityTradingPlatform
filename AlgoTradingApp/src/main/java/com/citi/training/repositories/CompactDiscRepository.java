package com.citi.training.repositories;


import com.citi.training.entities.CompactDisc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompactDiscRepository extends MongoRepository<CompactDisc, ObjectId> {

}
