package com.example.javaapp1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/welcome")
    public String Welcome() {
        return "HELLO_WORLD";
    }

    @GetMapping("/welcome-2")
    public String Welcome2() {
        return "HELLO_WORLD_2";
    }
}
