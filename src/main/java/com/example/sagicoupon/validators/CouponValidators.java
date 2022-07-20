package com.example.sagicoupon.validators;

import com.example.sagicoupon.dto.CouponDto;
import com.example.sagicoupon.enums.ErrorType;
import com.example.sagicoupon.exceptions.ServerException;
import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.repositories.CouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CouponValidators {

    private final CouponRepository couponRepository;

    public boolean addCouponValidation(Coupon coupon) {

        Optional.of(coupon)
                .map(Coupon::getTitle)
                .filter(Regex::textValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_TITLE_IS_INVALID));

        Optional.of(coupon)
                .map(Coupon::getDescription)
                .filter(Regex::textValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_DESCRIPTION_IS_INVALID));

//Coupon start date cannot be before the current date and 100 days after the current date//
        Optional.of(coupon)
                .map(Coupon::getStartDate)
                .filter(date -> date.isBefore(LocalDate.now().plusDays(100)) && date.isAfter(LocalDate.now()))
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_START_DATE_IS_INVALID));

        Optional.of(coupon)
                .map(Coupon::getAmount)
                .filter(integer -> coupon.getAmount() >= 0)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_AMOUNT_IS_LESS_THAN_1));

        Optional.of(coupon)
                .map(Coupon::getPrice)
                .filter(price -> coupon.getPrice() > 0)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_PRICE_IS_0));

        Optional.of(coupon)
                .map(Coupon::getImage)
                .filter(Regex::urlImageValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_IMAGE_URL_IS_INVALID));

        return true;
    }

    public boolean updateCouponValidation(Coupon coupon) {
        Optional<CouponDto> find = couponRepository.findById(coupon.getId());
        find.ifPresentOrElse((coupon1) -> {
                    System.out.println("Found coupon: " + coupon);
                },
                () -> {
                    throw new ServerException(ErrorType.COUPON_DOES_NOT_EXIST, String.valueOf(coupon.getCompanyId()));
                });

        Optional.of(coupon)
                .map(Coupon::getTitle)
                .filter(Regex::textValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_TITLE_IS_INVALID));

        Optional.of(coupon)
                .map(Coupon::getDescription)
                .filter(Regex::textValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_DESCRIPTION_IS_INVALID));

//Coupon start date cannot be before the current date and 100 days after the current date//
        Optional.of(coupon)
                .map(Coupon::getStartDate)
                .filter(date -> date.isBefore(LocalDate.now().plusDays(100)) && date.isAfter(LocalDate.now()))
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_START_DATE_IS_INVALID));

        Optional.of(coupon)
                .map(Coupon::getAmount)
                .filter(integer -> coupon.getAmount() >= 0)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_AMOUNT_IS_LESS_THAN_1));

        Optional.of(coupon)
                .map(Coupon::getPrice)
                .filter(price -> coupon.getPrice() > 0)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_PRICE_IS_0));

        Optional.of(coupon)
                .map(Coupon::getImage)
                .filter(Regex::urlImageValidation)
                .orElseThrow(() -> new ServerException(ErrorType.COUPON_IMAGE_URL_IS_INVALID));

        return true;
    }
}
