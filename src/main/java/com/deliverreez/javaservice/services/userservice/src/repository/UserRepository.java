package com.deliverreez.javaservice.services.userservice.src.repository;

import com.deliverreez.javaservice.services.userservice.src.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUsername(String username);

    Mono<User> findByEmail(String email);

    Flux<User> findByActiveTrue();

    Flux<User> findByRolesContaining(String role);
}
