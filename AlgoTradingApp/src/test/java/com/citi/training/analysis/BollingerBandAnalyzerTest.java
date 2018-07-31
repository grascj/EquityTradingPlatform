package com.citi.training.analysis;


import com.citi.training.entities.BollingerBands;
import com.citi.training.entities.MarketUpdate;
import com.citi.training.entities.Order;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.misc.Trend;
import com.citi.training.services.MarketUpdateService;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TradeService;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;


public class BollingerBandAnalyzerTest {

    @InjectMocks
    private BollingerBandsAnalyzer bollingerBandAnalyzer;

    @Mock
    private MarketUpdateService marketUpdateService;

    @Mock
    private StrategyService strategyService;

    @Mock
    private TradeService tradeService;

    @Before
    public void injectMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBollingerBandsSell() {

        //setup
        Mockito.when(marketUpdateService.movingStandardDeviation("goog", 10, 2.0)).thenReturn(2.0);
        Mockito.when(marketUpdateService.movingAverage("goog", 20)).thenReturn(40.0);
        Mockito.when(marketUpdateService.latestUpdateByTicker("goog")).thenReturn(new MarketUpdate(null, null, 40.50));
        BollingerBands bb = new BollingerBands("goog", 100, "test", 10.0, 10, 2.0);
        bb.setId(new ObjectId());
        //String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds


        //execute
        Order order = bollingerBandAnalyzer.analyze(bb);

        //check
        Assert.assertEquals(order.getPrice(), 40.5, 0);
        Assert.assertEquals(order.getSize(), 100);
        Assert.assertEquals(order.getstock(), "goog");
        Assert.assertEquals(order.isBuy(), false);

    }

    @Test
    public void testBollingerBandsBuy() {

        //setup
        Mockito.when(marketUpdateService.movingAverage("goog", 10)).thenReturn(40.0);
        Mockito.when(marketUpdateService.movingStandardDeviation("goog", 10, 2.0)).thenReturn(2.0);
        Mockito.when(marketUpdateService.latestUpdateByTicker("goog")).thenReturn(new MarketUpdate(null, null, 30.5));
        BollingerBands bb = new BollingerBands("goog", 100, "test", 10.0, 10, 2.0);
        bb.setId(new ObjectId());
        //String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds


        //execute
        Order order = bollingerBandAnalyzer.analyze(bb);

        //check
        Assert.assertEquals(order.getPrice(), 30.5, 0);
        Assert.assertEquals(order.getSize(), 100);
        Assert.assertEquals(order.getstock(), "goog");
        Assert.assertEquals(order.isBuy(), true);

    }

    @Test
    public void testBollingerBandsHold() {

        //setup
        Mockito.when(marketUpdateService.movingAverage("goog", 10)).thenReturn(40.0);
        Mockito.when(marketUpdateService.movingStandardDeviation("goog", 10, 2.0)).thenReturn(2.0);
        Mockito.when(marketUpdateService.latestUpdateByTicker("goog")).thenReturn(new MarketUpdate(null, null, 40.0));
        BollingerBands bb = new BollingerBands("goog", 100, "test", 10.0, 10, 2.0);
        bb.setId(new ObjectId());
        //String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds


        //execute
        Order order = bollingerBandAnalyzer.analyze(bb);

        //check


    }
}