package com.citi.training.feed;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Service
public class FeedReader {

    @Scheduled(fixedRate = 1000)
    public void pingFeed(){
        String[] tickers = {"aapl", "goog", "msft", "brk-a", "nsc"};
        String apiCall = "http://localhost:8085/quotes.csv?s=";
        //build out API call
        for(int i =0; i < tickers.length; i++) {
            apiCall += tickers[i];
            if(i < tickers.length - 1) {
                apiCall += ",";
            }
        }
        apiCall += "&f=p0";

        //exec API call
        URL url = null;
        try {
            url = new URL(apiCall);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine + " ");
            }
            in.close();
            con.disconnect();
            System.out.println(content);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
