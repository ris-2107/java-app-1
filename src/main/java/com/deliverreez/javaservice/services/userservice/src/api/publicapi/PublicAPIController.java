package com.deliverreez.javaservice.services.userservice.src.api.publicapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController("userServicePublicApiController")
@RequestMapping("/user-service/p")
public class PublicAPIController {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  private LocalDateTime bootTime;

  @PostConstruct
  public void init() {
    bootTime = LocalDateTime.now();
  }

  @GetMapping("/health")
  public HealthResponse getHealth() {
    LocalDateTime currentTime = LocalDateTime.now();
    Duration uptime = Duration.between(bootTime, currentTime);

    return new HealthResponse(currentTime, bootTime, uptime);
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class HealthResponse {
    private LocalDateTime currentTime;
    private LocalDateTime bootTime;
    private Duration uptime;
  }
}
