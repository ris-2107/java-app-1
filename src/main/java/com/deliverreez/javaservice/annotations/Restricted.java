package com.deliverreez.javaservice.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // This means the annotation will be available at runtime
public @interface Restricted {
  // String value() default "";
}
