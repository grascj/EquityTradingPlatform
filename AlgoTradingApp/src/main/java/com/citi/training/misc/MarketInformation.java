package com.citi.training.misc;


import com.citi.training.entities.Strategy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MarketInformation {


    private static List<Strategy> strategies;

    public static List<Strategy> getStrategies() {
        return strategies;
    }

    private static Set<String> tickers = ConcurrentHashMap.newKeySet();
    static {
        tickers.add("aapl");
        tickers.add("goog");
        tickers.add("msft");
        tickers.add("brk-a");
        tickers.add("nsc");
    }


    public static Set<String> getTickers() {
        return tickers;
    }

    public static void setTickers(Set<String> tickers) {
        MarketInformation.tickers = tickers;
    }

}
