package com.citi.training;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.citi.training")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}