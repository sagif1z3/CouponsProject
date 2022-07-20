package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.PurchaseDto;
import com.example.sagicoupon.model.Purchase;
import com.example.sagicoupon.services.CouponService;
import com.example.sagicoupon.services.UserService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PurchasesToPurchasesDtoConverter implements Converter<Purchase, PurchaseDto> {

    private final UserService userService;
    private final CouponService couponService;

    public PurchaseDto convertSave(@NotNull Purchase source){
        return PurchaseDto.builder()
                .userDto(userService.getById(source.getUserId()))
                .couponDto(couponService.getCouponDtoById(source.getCouponId()))
                .date(source.getDate())
                .amount(source.getAmount())
                .totalPrice(source.getTotalPrice())
                .build();
    }

    public PurchaseDto convertUpdate(@NotNull Purchase source){
        return PurchaseDto.builder()
                .id(source.getId())
                .userDto(userService.getById(source.getUserId()))
                .couponDto(couponService.getCouponDtoById(source.getCouponId()))
                .date(source.getDate())
                .amount(source.getAmount())
                .totalPrice(source.getTotalPrice())
                .build();
    }

    @Override
    public PurchaseDto convert(Purchase source) {
        return null;
    }
}
