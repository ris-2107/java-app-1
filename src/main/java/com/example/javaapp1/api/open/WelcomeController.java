package com.example.javaapp1.api.open;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {
    private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/welcome")
    public String Welcome() {
        log.info("/welcome path called");
        return "HELLO_WORLD";
    }

    @GetMapping("/welcome-2")
    public String Welcome2() throws InterruptedException {
        log.info("/welcome-2 path called");
        return "HELLO_WORLD_2";
    }
}
