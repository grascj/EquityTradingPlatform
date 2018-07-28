package com.citi.training.misc;


import com.citi.training.entities.Strategy;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MarketInformation {


    private static List<Strategy> strategies;

    public static List<Strategy> getStrategies() {
        return strategies;
    }

    private static List<String> tickers = new LinkedList();
    static {
        tickers.add("aapl");
        tickers.add("goog");
        tickers.add("msft");
        tickers.add("brk-a");
        tickers.add("nsc");
    }


    public static List<String> getTickers() {
        return tickers;
    }

    public static void setTickers(List<String> tickers) {
        MarketInformation.tickers = tickers;
    }

}
