package com.example.sagicoupon.model;

import com.example.sagicoupon.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserType userType;
    private Integer companyId;
    private transient String token;
}
