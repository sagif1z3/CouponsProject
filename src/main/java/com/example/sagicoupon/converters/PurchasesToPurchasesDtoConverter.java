package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.PurchaseDto;
import com.example.sagicoupon.model.Purchase;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchasesToPurchasesDtoConverter implements Converter<Purchase, PurchaseDto> {

    @Override
    public PurchaseDto convert(@NotNull Purchase source){
        return PurchaseDto.builder()
                .id(source.getId())
                .couponTitle(source.getCouponTitle())
                .date(source.getDate())
                .amount(source.getAmount())
                .price(source.getPrice())
                .totalPrice(source.getTotalPrice())
                .build();
    }
}
