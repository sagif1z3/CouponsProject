package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CouponDto;
import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.services.CompanyService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CouponToCouponDtoConverter implements Converter<Coupon, CouponDto> {

    private CompanyService companyService;

    @Autowired
    public CouponToCouponDtoConverter(CompanyService companyService) {
        this.companyService = companyService;
    }

    public CouponDto convertSave(@NotNull Coupon source) {
        return CouponDto.builder()
//                .id(source.getId())
                .companyDto(companyService.getCompanyDtoById(source.getCompanyId()))
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

    public CouponDto convertUpdate(@NotNull Coupon source) {
        return CouponDto.builder()
                .id(source.getId())
                .companyDto(companyService.getCompanyDtoById(source.getCompanyId()))
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

    @Override
    public CouponDto convert(Coupon source) {
        return null;
    }
}
