package com.deliverreez.javaservice.services.userservice.src.service;

import com.deliverreez.javaservice.services.userservice.src.model.User;
import com.deliverreez.javaservice.services.userservice.src.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {

  private final UserRepository userRepository;

  public Optional<User> getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<User> getActiveUsers() {
    return userRepository.findByActiveTrue();
  }

  public List<User> getUsersByRole(String role) {
    return userRepository.findByRolesContaining(role);
  }

  public Flux<User> getAllUsers() {
    // Retrieve all users and return List<User>
    return userRepository.findAll();
  }

  @Async
  @Transactional
  public void registerUserAsync(User user) {
    log.info("Processing user registration asynchronously...");
    // Perform user registration logic here
    Mono<User> registeredUser = registerUser(user);
    log.info("User registered asynchronously: {}", registeredUser);
  }

  @Override
  public Mono<User> registerUser(User user) {
    return userRepository.save(user);
  }
}
