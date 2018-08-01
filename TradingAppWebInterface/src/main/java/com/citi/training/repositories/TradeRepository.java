package com.citi.training.repositories;

import com.citi.training.entities.Trade;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepository extends MongoRepository<Trade, ObjectId> {

    List<Trade> findByResult(String result);

    List<Trade> findByStrategyId(String strategyId);

    List<Trade> findByIdAndAndTimeStampBetween(String strategyId, LocalDateTime anHrAgo, LocalDateTime now);
}
