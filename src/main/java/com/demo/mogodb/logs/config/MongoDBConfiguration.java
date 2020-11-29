package com.demo.mogodb.logs.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfiguration {

    @Bean
    public MongoClient mongoClient() {
        //return MongoClients.create("mongodb://localhost:27017");
        return MongoClients.create("mongodb://logs_db:27017");
    }
}