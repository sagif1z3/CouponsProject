package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CouponDto;
import com.example.sagicoupon.model.Coupon;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CouponToCouponDtoConverter implements Converter<Coupon, CouponDto> {

    @Override
    public CouponDto convert(@NotNull Coupon source){
        return CouponDto.builder()
                .id(source.getId())
                .category(source.getCategory())
                .title(source.getTitle())
                .description(source.getDescription())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .amount(source.getAmount())
                .price(source.getPrice())
                .image(source.getImage())
                .build();
    }

}
