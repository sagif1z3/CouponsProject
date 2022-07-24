package com.example.sagicoupon.validators;

import com.example.sagicoupon.enums.ErrorType;
import com.example.sagicoupon.exceptions.ServerException;
import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.model.Purchase;
import com.example.sagicoupon.model.User;
import com.example.sagicoupon.services.CouponService;
import com.example.sagicoupon.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PurchaseValidators {

    private CouponService couponService;
    private UserService userService;

    public boolean addPurchaseValidation(Purchase purchase) {
        Coupon coupon = couponService.findCouponById(purchase.getCouponId());
        User user = userService.findUserById(purchase.getUserId());
        Date currentDate = new Date(System.currentTimeMillis());
        purchase.setDate(currentDate);
        purchase.setTotalPrice(coupon.getPrice() * purchase.getAmount());

        Optional.ofNullable(user)
                .ifPresentOrElse((user1) -> {
                            System.out.println(
                                    "Found purchase: " + user);
                        },
                        () -> {
                            throw new ServerException(ErrorType.COUPON_DOES_NOT_EXIST);
                        });
        Optional.ofNullable(coupon)
                .ifPresentOrElse((coupon1) -> {
                            System.out.println(
                                    "Found purchase: " + coupon);
                        },
                        () -> {
                            throw new ServerException(ErrorType.COUPON_DOES_NOT_EXIST);
                        });

        Optional.of(purchase)
                .map(Purchase::getAmount)
                .filter(amount -> purchase.getAmount() > 0)
                .orElseThrow(() -> new ServerException(ErrorType.PURCHASE_AMOUNT_IS_LESS_THAN_1));


        Optional.of(purchase)
                .map(Purchase::getAmount)
                .filter(amount -> amount <= coupon.getAmount())
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_AMOUNT_IS_LESS_THAN_1));

        return true;
    }
}
