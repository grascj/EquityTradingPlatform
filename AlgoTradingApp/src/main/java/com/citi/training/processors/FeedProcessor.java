package com.citi.training.processors;


import com.citi.training.entities.MarketUpdate;
import com.citi.training.misc.MarketInformation;
import com.citi.training.services.MarketUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class FeedProcessor {

    @Autowired
    MarketUpdateService marketUpdateService;

    @Autowired
    MarketInformation marketInformation;


    @Value("${financial.feed.url}")
    String feedURL;

    @Scheduled(fixedRate = 250)
    public void pingFeed(){


        //build out API call



        //stream on a list is ordered
        String target = feedURL + "?s=" + marketInformation.getTickers().stream().reduce( (a, b) -> a + "," + b).get();

        // p0 is just the prices ordered the same as the request
        target += "&f=p0";

        //exec API call




        try {
            URL url = new URL(target);
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

            List<MarketUpdate> li = parseFeedResponse(content.toString());
            marketUpdateService.writeMarketUpdates(li);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public List<MarketUpdate> parseFeedResponse(String resp){
        Scanner respScanner = new Scanner(resp);
        LocalDateTime timestamp = LocalDateTime.now();
        return marketInformation.getTickers().stream().map(x -> new MarketUpdate(timestamp, x, respScanner.nextDouble())).collect(Collectors.toList());
    }





}
