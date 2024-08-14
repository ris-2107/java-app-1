package com.deliverreez.javaservice.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class Authority {

  private final String EXPECTED_HEADER = "ExpectedHeaderValue";

  @Before(
          "@within(com.deliverreez.javaservice.annotations.Authority) || @annotation(com.deliverreez.javaservice.annotations.Authority)")
  public void checkAuthority() {
    // This method cannot directly access the `ServerWebExchange` object in this way.
    // It should be managed at the request handling level, not the aspect level.
  }

  // You should handle headers in a reactive way in your handler methods or filters.
  public Mono<Void> checkAuthority(ServerWebExchange exchange) {
    return Mono.defer(() -> {
      String headerValue = exchange.getRequest().getHeaders().getFirst("X-Custom-Header");
      if (headerValue == null || !headerValue.equals(EXPECTED_HEADER)) {
        log.error("NOT Authorised to do operation");
        return exchange.getResponse().setComplete();
      } else {
        log.info("Authorised to do operation");
        return Mono.empty();
      }
    });
  }
}
