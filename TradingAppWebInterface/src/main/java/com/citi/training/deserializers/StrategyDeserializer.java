package com.citi.training.deserializers;

import com.citi.training.entities.BollingerBands;
import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class StrategyDeserializer  extends StdDeserializer<Strategy> {
    protected StrategyDeserializer() { this(null); }
    protected StrategyDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Strategy deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String ticker = node.get("ticker").asText();
        int numStocks = Integer.parseInt(node.get("stockQuantity").asText());
        String exitRule = node.get("exitRule").asText();
        double exitPercentage = Double.parseDouble(node.get("exitPercentage").asText());
        String name = node.get("name").asText();

        //if TwoMovingAverages
        if(node.has("shortAverageSeconds") && node.has("longAverageSeconds")) {
            int shortVal = Integer.parseInt(node.get("shortAverageSeconds").asText());
            int longVal = Integer.parseInt(node.get("longAverageSeconds").asText());

            return new TwoMovingAverages(ticker, numStocks, exitRule, exitPercentage, shortVal, longVal, name);
        }
        else {
            //must be bollinger
            int avgSec = Integer.parseInt(node.get("avgSeconds").asText());
            double std = Double.parseDouble(node.get("standardDeviation").asText());

            return new BollingerBands(ticker, numStocks, exitRule, exitPercentage,  avgSec, std, name);
        }


    }

}


