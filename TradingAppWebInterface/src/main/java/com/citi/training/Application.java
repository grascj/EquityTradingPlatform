package com.citi.training;


import com.citi.training.entities.MarketUpdate;
import com.citi.training.repositories.MarketUpdateRepository;
import com.citi.training.repositories.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.citi.training")
public class Application  {
    //implements CommandLineRunner
    @Autowired
    private MarketUpdateRepository repository;

    @Autowired
    private StrategyRepository stratRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


/*    @Override
    public void run(String... args) throws Exception {


        // fetch all customers
        System.out.println("Feed data found with findAll():");
        System.out.println("-------------------------------");
        for (MarketUpdate mu : repository.findAll()) {
            System.out.println(mu);
        }
        System.out.println();

    }*/
}