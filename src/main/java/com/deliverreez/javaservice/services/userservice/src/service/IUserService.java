package com.deliverreez.javaservice.services.userservice.src.service;

import com.deliverreez.javaservice.services.userservice.src.model.User;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {
    User registerUser(User user);
}
