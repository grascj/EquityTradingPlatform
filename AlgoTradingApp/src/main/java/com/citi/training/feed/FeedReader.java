package com.citi.training.feed;


import com.citi.training.entities.MarketUpdate;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Arrays;
import java.util.List;

@Service
public class FeedReader {

    @Autowired
    MarketRecorder marketRecorder;

    @Scheduled(fixedRate = 1000)
    public void pingFeed(){
        String[] tickers = {"aapl", "goog", "msft", "brk-a", "nsc"};
        List<String> tickList = Arrays.asList(tickers);
        String apiCall = "http://localhost:8085/quotes.csv?s=";
        //build out API call


        //stream on a list is ordered
        apiCall += tickList.stream().reduce( (a, b) -> a + "," + b);

        // p0 is just the prices ordered the same as the request
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

            List<MarketUpdate> li = marketRecorder.parseFeedResponse(content.toString(), tickList);
            marketRecorder.writeTickers(li);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
