package com.deliverreez.javaservice.services.userservice.src.service;

import com.deliverreez.javaservice.services.userservice.src.model.User;
import com.deliverreez.javaservice.services.userservice.src.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    public Mono<User>  getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Mono<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Flux<User> getActiveUsers() {
        return userRepository.findByActiveTrue();
    }

    public  Flux<User> getUsersByRole(String role) {
        return userRepository.findByRolesContaining(role);
    }

    public Flux<User> getAllUsers() {
        // Retrieve all users asynchronously and return Flux<User>
        return userRepository.findAll();
    }


    @Override
    public Mono<User> registerUser(User user) {
        // Save the user asynchronously and return the Mono<User> returned by userRepository.save(user)
        return userRepository.save(user);
    }

}

