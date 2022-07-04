package com.example.sagicoupon.dto;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
@Table(name = "customeres")
public class CustomerDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserDto userDto;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "amount_of_Kids", nullable = false)
    private int amountOfKids;

    @Column(name = "birth_date", nullable = false, columnDefinition = "DATE")
    private Date birthDate;

}
