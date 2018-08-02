package com.citi.training.analysis;

import com.citi.training.entities.*;
import com.citi.training.misc.StockAction;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ExitTest {


    @Test
    public void testExitOnProfitDefault() {

        //setup
        TwoMovingAverages tma = new TwoMovingAverages("goog", 1, "loss", .2, 4, 7);
        tma.setProfitAndLoss(1.0);
        tma.setInitialValue(100.0);
        TwoMovingAveragesAnalyzer tmaa = new TwoMovingAveragesAnalyzer();

        Assert.assertEquals(tmaa.shouldExit(tma, 1.0, .2, "loss"), true);

    }


    @Test
    public void testExitOnProfitLoss() {

        //setup
        TwoMovingAverages tma = new TwoMovingAverages("goog", 1, "loss", .3, 4, 7);
        tma.setProfitAndLoss(1.0);
        tma.setInitialValue(100.0);
        TwoMovingAveragesAnalyzer tmaa = new TwoMovingAveragesAnalyzer();

        Assert.assertEquals(tmaa.shouldExit(tma, 1.0, .3, "loss"), true);

    }


    @Test
    public void testExitOnProfit() {

        //setup
        TwoMovingAverages tma = new TwoMovingAverages("goog", 1, "profit", .3, 4, 7);
        tma.setProfitAndLoss(200.0);
        tma.setInitialValue(100.0);
        TwoMovingAveragesAnalyzer tmaa = new TwoMovingAveragesAnalyzer();

        Assert.assertEquals(tmaa.shouldExit(tma, 1.0, .3, "profit"), true);

    }






}

