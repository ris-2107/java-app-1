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
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime bootTime;

    @PostConstruct
    public void init() {
        bootTime = LocalDateTime.now();
    }

    @GetMapping("/health")
    public HealthResponse getHealth() {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration uptime = Duration.between(bootTime, currentTime);

        return new HealthResponse(
                currentTime.format(formatter),
                bootTime.format(formatter),
                formatDuration(uptime)
        );
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long millis = duration.toMillisPart();

        StringBuilder formatted = new StringBuilder();
        if (hours > 0) {
            formatted.append(hours).append("h ");
        }
        if (minutes > 0) {
            formatted.append(minutes).append("m ");
        }
        if (seconds > 0) {
            formatted.append(seconds).append("s ");
        }
        if (millis > 0) {
            formatted.append(millis).append("ms");
        }

        return formatted.toString().trim();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HealthResponse {
        private String currentTime;
        private String bootTime;
        private String uptime;
    }
}
