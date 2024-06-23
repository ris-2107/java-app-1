package com.deliverreez.javaservice.services.userservice.src.api.restrictedapi;

import com.deliverreez.javaservice.annotations.Authority;
import com.deliverreez.javaservice.dtos.DelivereezResponse;
import com.deliverreez.javaservice.services.userservice.src.model.User;
import com.deliverreez.javaservice.services.userservice.src.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user-service/r")
@Authority
@AllArgsConstructor
public class RestrictedApiController {


    private UserService userService;

    @PostMapping("/register-user")
    public Mono<ResponseEntity<DelivereezResponse<User>>> registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user)
                .map(registeredUser -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new DelivereezResponse<>("success", "User registered successfully", registeredUser)));
    }

    @GetMapping("/users/email/{email}")
    public Mono<ResponseEntity<DelivereezResponse<User>>> getUserByEmail(@PathVariable String email) {
        return userService.getUsersByEmail(email)
                .map(user -> ResponseEntity.ok()
                        .body(new DelivereezResponse<>("success", "User found", user)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public Mono<ResponseEntity<DelivereezResponse<Flux<User>>>> getAllUsers() {
        return userService.getAllUsers()
                .collectList()
                .map(users -> ResponseEntity.ok()
                        .body(new DelivereezResponse<>("success", "All users retrieved", Flux.fromIterable(users))));
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
