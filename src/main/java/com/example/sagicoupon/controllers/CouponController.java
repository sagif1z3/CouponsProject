package com.example.sagicoupon.controllers;

import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.services.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
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

    @DeleteMapping("/{id}/delete")
    public void deleteCouponById(@PathVariable Long id){
        couponService.deleteCouponById(id);
    }
}

