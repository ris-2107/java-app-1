package com.deliverreez.javaservice.services.userservice.src.service;

import com.deliverreez.javaservice.services.userservice.src.model.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface IUserService {
    Mono<User> registerUser(User user);
}
