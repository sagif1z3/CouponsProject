package com.example.sagicoupon.services;

import com.example.sagicoupon.dto.CouponDto;
import com.example.sagicoupon.model.Coupon;
import java.util.List;

public interface CouponService {

    Coupon addCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();

    Coupon findCouponById(long id);

    Coupon updateCoupon(Coupon coupon);

    void deleteCouponById(long id);

    List<CouponDto> getExpiredCoupons();

    CouponDto getCouponDtoById (long id);
}
