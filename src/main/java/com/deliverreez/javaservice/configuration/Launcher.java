package com.deliverreez.javaservice.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.deliverreez.javaservice.services.userservice.src.repository")
@ComponentScan(basePackages = {"com.deliverreez.javaservice.configuration", "com.deliverreez.javaservice.repos.userservice", "com.deliverreez.javaservice.services.userservice.src.api", "com.deliverreez.javaservice.services.userservice.src.service", "com.deliverreez.javaservice.services.userservice.src.repository", "com.deliverreez.javaservice.annotations"})

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
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "DB_A");
    }

//    @Bean
//    public UserRepository userRepository () {
//        return new UserRepository() {
//            @Override
//            public <S extends User> Mono<S> save(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Flux<S> saveAll(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Flux<S> saveAll(Publisher<S> entityStream) {
//                return null;
//            }
//
//            @Override
//            public Mono<User> findById(String s) {
//                return null;
//            }
//
//            @Override
//            public Mono<User> findById(Publisher<String> id) {
//                return null;
//            }
//
//            @Override
//            public Mono<Boolean> existsById(String s) {
//                return null;
//            }
//
//            @Override
//            public Mono<Boolean> existsById(Publisher<String> id) {
//                return null;
//            }
//
//            @Override
//            public Flux<User> findAll() {
//                return null;
//            }
//
//            @Override
//            public Flux<User> findAllById(Iterable<String> strings) {
//                return null;
//            }
//
//            @Override
//            public Flux<User> findAllById(Publisher<String> idStream) {
//                return null;
//            }
//
//            @Override
//            public Mono<Long> count() {
//                return null;
//            }
//
//            @Override
//            public Mono<Void> deleteById(String s) {
//                return null;
//            }
//
//            @Override
//            public Mono<Void> deleteById(Publisher<String> id) {
//                return null;
//            }
//
//            @Override
//            public Mono<Void> delete(User entity) {
//                return null;
//            }
//
//            @Override
//            public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
//                return null;
//            }
//
//            @Override
//            public Mono<Void> deleteAll(Iterable<? extends User> entities) {
//                return null;
//            }
//
//            @Override
//            public Mono<Void> deleteAll(Publisher<? extends User> entityStream) {
//                return null;
//            }
//
//            @Override
//            public Mono<Void> deleteAll() {
//                return null;
//            }
//
//            @Override
//            public Flux<User> findAll(Sort sort) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Mono<S> insert(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Flux<S> insert(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Flux<S> insert(Publisher<S> entities) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Mono<S> findOne(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Flux<S> findAll(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Flux<S> findAll(Example<S> example, Sort sort) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Mono<Long> count(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> Mono<Boolean> exists(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends User, R, P extends Publisher<R>> P findBy(Example<S> example, Function<FluentQuery.ReactiveFluentQuery<S>, P> queryFunction) {
//                return null;
//            }
//
//            @Override
//            public Mono<User> findByUsername(String username) {
//                return null;
//            }
//
//            @Override
//            public Mono<User> findByEmail(String email) {
//                return null;
//            }
//
//            @Override
//            public Flux<User> findByActiveTrue() {
//                return null;
//            }
//
//            @Override
//            public Flux<User> findByRolesContaining(String role) {
//                return null;
//            }
//            // Implement methods if needed, or return null
//        };
//    }
}
