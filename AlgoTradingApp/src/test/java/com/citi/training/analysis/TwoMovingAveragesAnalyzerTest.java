package com.citi.training.analysis;


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



public class TwoMovingAveragesAnalyzerTest {

    @InjectMocks
    private TwoMovingAveragesAnalyzer twoMovingAveragesAnalyzer;

    @Mock
    private MarketUpdateService marketUpdateService;

    @Mock
    private StrategyService strategyService;

    @Mock
    private TradeService tradeService;


    @Before
    public void injectMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMovingAveragesSell() {
        //setup
        Mockito.when(marketUpdateService.movingAverage("goog", 10 )).thenReturn(25.0);
        Mockito.when(marketUpdateService.movingAverage("goog", 20 )).thenReturn(40.0);
        Mockito.when(marketUpdateService.latestUpdateByTicker("goog")).thenReturn(new MarketUpdate(null,null,40.5));
        TwoMovingAverages tma = new TwoMovingAverages("goog", 100, "test", 10.0, 10, 20);
        tma.setCurrentTrend(Trend.UPWARD);
        tma.setId(new ObjectId());
        //String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds


        //execute
        Order order = twoMovingAveragesAnalyzer.analyze(tma);

        //check
        Assert.assertEquals(order.getPrice(),40.5,0);
        Assert.assertEquals(tma.getCurrentTrend(), Trend.DOWNWARD);
        Assert.assertEquals(order.getSize(), 100);
        Assert.assertEquals(order.getstock(), "goog");
        Assert.assertEquals(order.isBuy(), false);
    }


    @Test
    public void testMovingAveragesBuy() {
        //setup
        Mockito.when(marketUpdateService.movingAverage("goog", 10 )).thenReturn(40.0);
        Mockito.when(marketUpdateService.movingAverage("goog", 20 )).thenReturn(25.0);
        Mockito.when(marketUpdateService.latestUpdateByTicker("goog")).thenReturn(new MarketUpdate(null,null,38.0));
        TwoMovingAverages tma = new TwoMovingAverages("goog", 100, "test", 10.0, 10, 20);
        tma.setCurrentTrend(Trend.DOWNWARD  );
        tma.setId(new ObjectId());
        //String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds


        //execute
        Order order = twoMovingAveragesAnalyzer.analyze(tma);

        //check
        Assert.assertEquals(order.getPrice(),38.0,0);
        Assert.assertEquals(tma.getCurrentTrend(), Trend.UPWARD );
        Assert.assertEquals(order.getSize(), 100);
        Assert.assertEquals(order.getstock(), "goog");
        Assert.assertEquals(order.isBuy(), true);
    }



    @Test
    public void testMovingAveragesHold() {
        //setup
        Mockito.when(marketUpdateService.movingAverage("goog", 10 )).thenReturn(40.0);
        Mockito.when(marketUpdateService.movingAverage("goog", 20 )).thenReturn(25.0);
        Mockito.when(marketUpdateService.latestUpdateByTicker("goog")).thenReturn(new MarketUpdate(null,null,38.0));
        TwoMovingAverages tma = new TwoMovingAverages("goog", 100, "test", 10.0, 10, 20);
        tma.setId(new ObjectId());
        tma.setCurrentTrend(Trend.UPWARD      );
        //String ticker, Integer stockQuanity, String exitRule, Double exitPercentage, Integer shortAverageSeconds, Integer longAverageSeconds


        //execute
        Order order = twoMovingAveragesAnalyzer.analyze(tma);

        //check

        Assert.assertEquals(tma.getCurrentTrend(), Trend.UPWARD     );

    }
}