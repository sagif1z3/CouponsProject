package com.example.sagicoupon.model;

import com.example.sagicoupon.enums.UserType;
import lombok.Data;


@Data
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private UserType userType;
    private Integer companyId;
}
