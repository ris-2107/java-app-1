package com.deliverreez.javaservice.api.publicapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p")
@Slf4j
public class PublicAPIController {

    @GetMapping("/health")
    public String HealthApi() {
        log.info("/health path called");
        return "Health is OK";
    }
}
