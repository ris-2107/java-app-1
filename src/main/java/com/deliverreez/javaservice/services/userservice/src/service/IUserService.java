package com.deliverreez.javaservice.services.userservice.src.service;

import com.deliverreez.javaservice.services.userservice.src.model.User;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<User> registerUser(User user);
}
