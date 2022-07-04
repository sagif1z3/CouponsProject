package com.example.sagicoupon.dto;

import com.example.sagicoupon.enums.Category;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "coupon")
@Table(name = "coupons")
public class CouponDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne
    private CompanyDto companyDto;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "couponDto")
    private List<PurchaseDto> purchaseDto;
}
