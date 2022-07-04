package com.example.sagicoupon.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Purchase {
    private long id;
    private Long userId;
    private String couponTitle;
    private Date date;
    private int amount;
    private int price;
    private int totalPrice;
}
