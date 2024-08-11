package com.deliverreez.javaservice.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(
    basePackages = "com.deliverreez.javaservice.services.userservice.src.repository")
@ComponentScan(
    basePackages = {
      "com.deliverreez.javaservice.configuration",
      "com.deliverreez.javaservice.repos.userservice",
      "com.deliverreez.javaservice.services.userservice.src.api",
      "com.deliverreez.javaservice.services.userservice.src.service",
      "com.deliverreez.javaservice.services.userservice.src.repository",
      "com.deliverreez.javaservice.annotations",
      "com.deliverreez.javaservice.aspects"
    })
public class Launcher {

  @Value("${MONGODB_URI}")
  private String mongoUri;

  public static void main(String[] args) {
    SpringApplication.run(Launcher.class, args);
  }

  @Bean
  public MongoClient mongoClient() {
    return MongoClients.create(mongoUri);
  }

  @Bean
  public ReactiveMongoTemplate reactiveMongoTemplate() {
    return new ReactiveMongoTemplate(mongoClient(), "DB_A");
  }
}
