package com.deliverreez.javaservice.services.userservice.src.api.restrictedapi;

// import com.deliverreez.javaservice.annotations.Authority;
import com.deliverreez.javaservice.annotations.Authority;
import com.deliverreez.javaservice.annotations.Restricted;
import com.deliverreez.javaservice.dtos.DelivereezResponse;
import com.deliverreez.javaservice.services.userservice.src.model.User;
import com.deliverreez.javaservice.services.userservice.src.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@Restricted
@RequestMapping("/user-service/r")
@RequiredArgsConstructor
@Slf4j
public class RestrictedApiController {

  private final UserService userService;

  @PostMapping("/register-user")
  @Authority
  public ResponseEntity<DelivereezResponse<User>> registerUser(@Valid @RequestBody User user) {
    log.info("Request received to register user asynchronously");
    userService.registerUserAsync(user);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(new DelivereezResponse<>("accepted", "User registration request accepted", null));
  }

  @GetMapping("/users/email/{email}")
  public ResponseEntity<DelivereezResponse<User>> getUserByEmail(@PathVariable String email) {
    Optional<User> userOpt = userService.getUserByEmail(email);
    if (userOpt.isPresent()) {
      return ResponseEntity.ok(new DelivereezResponse<>("success", "User found", userOpt.get()));
    } else {
      log.warn("User not found with email: {}", email);
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new DelivereezResponse<>("error", "User not found", null));
    }
  }

  @GetMapping("/users")
  public Mono<ResponseEntity<DelivereezResponse<Flux<User>>>> getAllUsers() {
    Mono<ResponseEntity<DelivereezResponse<Flux<User>>>> responseEntityMono =
        Mono.just(
                ResponseEntity.ok(
                    new DelivereezResponse<>(
                        "success", "All users retrieved", userService.getAllUsers())))
            .onErrorResume(
                ex -> {
                  log.error("Error occurred while fetching users", ex);
                  return Mono.just(
                      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body(
                              new DelivereezResponse<>(
                                  "error", "Failed to fetch users", Flux.empty())));
                });

    responseEntityMono.subscribe(
        d -> {
          System.out.println(d);
        });
    return responseEntityMono;
  }
}
