package com.example.sagicoupon.model;

import com.example.sagicoupon.dto.UserDto;
import lombok.Data;
import java.sql.Date;

@Data
public class Customer {
    private long id;
    private UserDto userDto;
    private String address;
    private int amountOfKids;
    private Date birthDate;
}
