package com.citi.training.processors;

import com.citi.training.misc.MarketInformation;
import com.citi.training.services.MarketUpdateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

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
    public void pingFeed() {
        List<String> tickers = new LinkedList<>();
        tickers.add("goog");
        tickers.add("appl");
        //Mockito.when(marketInformation.getTickers()).thenReturn(tickers);


    }


    @Test
    public void parseFeedResponse() {
    }
}