package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CouponDto;
import com.example.sagicoupon.model.Coupon;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CouponDtoToCouponConverter implements Converter<CouponDto, Coupon> {

    @Override
    public Coupon convert(@NotNull CouponDto source) {
        Coupon coupon = new Coupon();
        coupon.setId(source.getId());
        coupon.setCompanyId(source.getCompanyDto().getId());
        coupon.setCategory(source.getCategory());
        coupon.setTitle(source.getTitle());
        coupon.setDescription(source.getDescription());
        coupon.setStartDate(source.getStartDate());
        coupon.setEndDate(source.getEndDate());
        coupon.setAmount(source.getAmount());
        coupon.setPrice(source.getPrice());
        coupon.setImage(source.getImage());
        return coupon;
    }
}
