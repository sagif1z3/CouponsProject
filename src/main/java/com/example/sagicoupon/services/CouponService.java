package com.example.sagicoupon.services;

import com.example.sagicoupon.model.Coupon;
import java.util.List;

public interface CouponService {

    Coupon addCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();

    Coupon findCouponById(Long id);

    Coupon updateCoupon(Coupon coupon);

    void deleteCouponById(Long id);
}
