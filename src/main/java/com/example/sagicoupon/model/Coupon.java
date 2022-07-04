package com.example.sagicoupon.model;

import com.example.sagicoupon.enums.Category;
import lombok.Data;
import java.sql.Date;

@Data
public class Coupon {
    private Long id;
    private Long companyId;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
}
