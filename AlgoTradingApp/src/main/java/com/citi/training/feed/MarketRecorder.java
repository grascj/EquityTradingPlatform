package com.citi.training.feed;


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
public class MarketRecorder {

    @Autowired
    MarketUpdateRepository marketUpdateRepository;


    public List<MarketUpdate> parseFeedResponse(String resp, List<String> tickList){
        Scanner respScanner = new Scanner(resp);
        LocalDateTime timestamp = LocalDateTime.now();
        return tickList.stream().map(x -> new MarketUpdate(timestamp, x, respScanner.nextDouble())).collect(Collectors.toList());
    }


    public void writeTickers(List<MarketUpdate> tickers) {
        marketUpdateRepository.save(tickers);
    }

}
