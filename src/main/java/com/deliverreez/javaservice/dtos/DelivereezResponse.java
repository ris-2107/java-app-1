package com.deliverreez.javaservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelivereezResponse<T> {
    private String status;
    private String message;
    private T data;
}
