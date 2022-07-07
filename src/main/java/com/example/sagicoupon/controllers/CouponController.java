package com.example.sagicoupon.controllers;

import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.services.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/addnew")
    public Coupon addNewCoupon(@RequestBody Coupon coupon){
        return couponService.addCoupon(coupon);
    }

    @GetMapping("/{id}/show")
    public Coupon showCouponById(@PathVariable Long id){
            return couponService.findCouponById(id);
    }

    @GetMapping("/showall")
    public List<Coupon> showAllCoupons() {
        return couponService.getAllCoupons();
    }

    @PutMapping("/update")
    public Coupon updateCoupon (@RequestBody Coupon coupon){
        return couponService.updateCoupon(coupon);
    }

    @GetMapping("/{id}/delete")
    public void deleteCouponById(@PathVariable Long id){
        couponService.deleteCouponById(id);
    }
}

