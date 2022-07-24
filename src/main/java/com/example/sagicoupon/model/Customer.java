package com.example.sagicoupon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    private long id;
    private User user;
    private String address;
    private int amountOfKids;
    private LocalDate birthDate;
}
