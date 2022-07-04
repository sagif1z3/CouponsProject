package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CompanyDto;
import com.example.sagicoupon.model.Company;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CompanyDtoToCompanyConverter implements Converter<CompanyDto, Company> {

    @Override
    public Company convert(@NotNull CompanyDto source){
        Company company = new Company();
        company.setId(source.getId());
        company.setAddress(source.getAddress());
        company.setName(source.getName());
        company.setPhoneNumber(source.getPhoneNumber());
        return company;
    }
}
