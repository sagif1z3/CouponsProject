package com.example.sagicoupon.model;

import com.example.sagicoupon.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
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
