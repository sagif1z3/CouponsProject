package com.example.sagicoupon.dto;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Getter
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

    @Column(name = "coupon_title", nullable = false)
    private String couponTitle;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @ManyToOne
    private CouponDto couponDto;

    @ManyToOne
    private UserDto userDto;
}
