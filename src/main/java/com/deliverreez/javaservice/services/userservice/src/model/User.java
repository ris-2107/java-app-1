package com.deliverreez.javaservice.services.userservice.src.model;

import com.mongodb.lang.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String id;

    @NotBlank(message = "Username is required")
    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private List<String> roles;

    private boolean active = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Nullable
    private Address address;

    @Nullable
    private Metadata metadata;

}

@Data
class Address {

    private String street;

    private String city;

    private String zipcode;

}

@Data
class Metadata {

    private LocalDateTime preferredDateFormat;
}

