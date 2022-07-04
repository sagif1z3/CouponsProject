package com.example.sagicoupon.dto;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "company")
@Table(name = "companies")
public class CompanyDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "companyDto")
    private List<UserDto> userDto;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "companyDto")
    private List<CouponDto> couponDto;
}
