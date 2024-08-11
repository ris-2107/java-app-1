package com.deliverreez.javaservice.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RestrictedApiChecks {
  @Before(
      "@within(com.deliverreez.javaservice.annotations.Restricted) || @annotation(com.deliverreez.javaservice.annotations.Restricted)")
  public void checkRestrictions() {
    log.info("Restrictions invoked");
  }
}
