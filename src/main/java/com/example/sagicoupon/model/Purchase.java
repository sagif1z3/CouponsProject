package com.example.sagicoupon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private long id;
    private long userId;
    private long couponId;
    private Date date;
    private int amount;
    private float totalPrice;
}
