package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CompanyDto;
import com.example.sagicoupon.model.Company;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CompanyToCompanyDtoConverter implements Converter<Company, CompanyDto> {

    public CompanyDto convertSave(@NotNull Company source){
        return CompanyDto.builder()
                .address(source.getAddress())
                .name(source.getName())
                .phoneNumber(source.getPhoneNumber())
                .build();
    }

    public CompanyDto convertUpdate(@NotNull Company source){
        return CompanyDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .name(source.getName())
                .phoneNumber(source.getPhoneNumber())
                .build();
    }

    @Override
    public CompanyDto convert(Company source) {
        return null;
    }
}
