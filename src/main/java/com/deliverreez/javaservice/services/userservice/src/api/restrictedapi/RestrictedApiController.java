package com.deliverreez.javaservice.services.userservice.src.api.restrictedapi;

import com.deliverreez.javaservice.annotations.Authority;
import com.deliverreez.javaservice.dtos.DelivereezResponse;
import com.deliverreez.javaservice.services.userservice.src.model.User;
import com.deliverreez.javaservice.services.userservice.src.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user-service/r")
@Authority
@RequiredArgsConstructor
public class RestrictedApiController {

    private static final Logger log = LoggerFactory.getLogger(RestrictedApiController.class);
    private final UserService userService;

    @PostMapping("/register-user")
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
    public CompletableFuture<ResponseEntity<DelivereezResponse<List<User>>>> getAllUsers() {
        return CompletableFuture.supplyAsync(() -> {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(new DelivereezResponse<>("success", "All users retrieved", users));
        }).exceptionally(ex -> {
            log.error("Error occurred while fetching users", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DelivereezResponse<>("error", "Failed to fetch users", null));
        });
    }
}
