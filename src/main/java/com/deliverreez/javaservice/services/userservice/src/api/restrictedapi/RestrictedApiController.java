package com.deliverreez.javaservice.services.userservice.src.api.restrictedapi;

import com.deliverreez.javaservice.annotations.Authority;
import com.deliverreez.javaservice.dtos.DelivereezResponse;
import com.deliverreez.javaservice.services.userservice.src.model.User;
import com.deliverreez.javaservice.services.userservice.src.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user-service/r")
@Authority
@AllArgsConstructor
public class RestrictedApiController {

    private static final Logger log = LoggerFactory.getLogger(RestrictedApiController.class);
    private final UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<DelivereezResponse<User>> registerUser(@Valid @RequestBody User user) {
        log.info("Request received to register user");
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DelivereezResponse<>("success", "User registered successfully", registeredUser));
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<DelivereezResponse<User>> getUserByEmail(@PathVariable String email) {
        Optional<User> userOpt = userService.getUserByEmail(email);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok().body(new DelivereezResponse<>("success", "User found", userOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DelivereezResponse<>("error", "User not found", null));
        }
    }

    @GetMapping("/users")
    public CompletableFuture<ResponseEntity<DelivereezResponse<List<User>>>> getAllUsers() {
        log.info("Start getAllUsers");

        return CompletableFuture.supplyAsync(() -> {
            List<User> users = userService.getAllUsers();
            DelivereezResponse<List<User>> response = new DelivereezResponse<>("success", "All users retrieved", users);
            log.info("End getAllUsers");
            return ResponseEntity.ok(response);
        }).exceptionally(ex -> {
            log.error("Error occurred while fetching users", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DelivereezResponse<>("error", "Failed to fetch users", null));
        });
    }

    @PostMapping("/get-user")
    public DelivereezResponse<Map<String, Object>> getSampleJson(@RequestBody Map<String, String> input) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 12345);
        data.put("name", "Sample User");
        data.put("email", "sample.user@example.com");
        data.put("roles", new String[]{"USER", "ADMIN"});

        Map<String, Object> address = new HashMap<>();
        address.put("street", "123 Sample Street");
        address.put("city", "Sample City");
        address.put("zipcode", "12345");

        data.put("address", address);

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("created_at", "2023-06-23T10:00:00Z");
        metadata.put("updated_at", "2024-06-23T10:00:00Z");
        metadata.put("status", "active");

        data.put("metadata", metadata);

        return new DelivereezResponse<>("success", "This is a sample response", data);
    }
}
