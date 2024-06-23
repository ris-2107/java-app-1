package com.deliverreez.javaservice.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication(scanBasePackages = {"com.deliverreez.javaservice.configuration", "com.deliverreez.javaservice.api"}, exclude = {MongoAutoConfiguration.class})
public class Launcher {

    @Value("${MONGODB_URI}")
    private String mongoUri;

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean("dbConnectionBean")
    public MongoTemplate dbConnection() {
        MongoClient mongoClient = MongoClients.create(mongoUri);
        return new MongoTemplate(mongoClient, "DB_A");
    }

}
