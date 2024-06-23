package com.deliverreez.javaservice.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Aspect
@Component
@Slf4j
public class Authority {

    @Autowired
    private HttpServletRequest request;

    @Before("@within(com.deliverreez.javaservice.annotations.Authority) || @annotation(com.deliverreez.javaservice.annotations.Authority)")
    public void checkAuthority() {  // Removed the argument
        String headerValue = request.getHeader("X-Custom-Header");
        if (headerValue == null || !headerValue.equals("ExpectedHeaderValue")) {
            log.info("NOT Authorised to do operation");
        } else {
            log.info("Authorised to do operation");
        }
    }
}

