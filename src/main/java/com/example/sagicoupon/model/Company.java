package com.example.sagicoupon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Company {
    private long id;
    private String address;
    private String name;
    private String phoneNumber;
}
