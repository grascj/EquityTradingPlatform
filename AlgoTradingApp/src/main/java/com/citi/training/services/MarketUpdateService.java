package com.citi.training.services;


import com.citi.training.entities.MarketUpdate;
import com.citi.training.repositories.MarketUpdateRepository;
import com.mongodb.BSONTimestampCodec;
import org.bson.types.BSONTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@Service
public class MarketUpdateService {

    @Autowired
    MarketUpdateRepository marketUpdateRepository;



    public void writeMarketUpdates(List<MarketUpdate> tickers) {
        marketUpdateRepository.save(tickers);
    }

}
