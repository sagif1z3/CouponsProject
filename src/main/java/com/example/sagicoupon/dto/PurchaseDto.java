package com.example.sagicoupon.dto;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "purchase")
@Table(name = "purchases")
public class PurchaseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    @ManyToOne
    private CouponDto couponDto;

    @ManyToOne
    private UserDto userDto;
}
