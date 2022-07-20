package com.example.sagicoupon.dto;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
@Table(name = "customers")
public class CustomerDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "amount_of_Kids", nullable = false)
    private int amountOfKids;

    @Column(name = "birth_date", nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "id", referencedColumnName = "id")
    @MapsId
    private UserDto userDto;
}
