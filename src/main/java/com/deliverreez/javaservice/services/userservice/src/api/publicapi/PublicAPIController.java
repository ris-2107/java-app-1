package com.deliverreez.javaservice.services.userservice.src.api.publicapi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userServicePublicApiController")
@RequestMapping("/user-service/p")
public class PublicAPIController {

    @GetMapping("/")
    public String getUsers() {
        return "25";
    }
}
