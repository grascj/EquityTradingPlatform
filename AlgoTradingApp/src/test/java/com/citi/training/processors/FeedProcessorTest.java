package com.citi.training.processors;

import com.citi.training.misc.MarketInformation;
import com.citi.training.services.MarketUpdateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FeedProcessorTest {

    @InjectMocks
    private FeedProcessor feedProcessor;

    @Mock
    private MarketUpdateService marketUpdateService;

    @Mock
    private MarketInformation marketInformation;

    @Before
    public void injectMocks(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testParseFeedResponse() {
        Set<String> tickers = new HashSet<>();
        tickers.add("goog");
        tickers.add("appl");
        marketInformation.setTickers(tickers);


    }

    /*
        public List<MarketUpdate> parseFeedResponse(String resp){
        Scanner respScanner = new Scanner(resp);
        LocalDateTime timestamp = LocalDateTime.now();
        return marketInformation.getTickers().stream().map(x -> new MarketUpdate(timestamp, x, respScanner.nextDouble())).collect(Collectors.toList());
    }
     */

    @Test
    public void testFormURL() {
        Set<String> tickers = new HashSet<>();
        tickers.add("goog");
        tickers.add("appl");
        marketInformation.setTickers(tickers);

        String res = feedProcessor.formURL();

        Assert.assertEquals(res, feedProcessor.feedURL+"?s=goog,appl&f=p0");
    }


}