package com.example.sagicoupon.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorBean {

    private int status;
    private String message;
}
