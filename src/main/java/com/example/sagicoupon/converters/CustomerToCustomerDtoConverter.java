package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CustomerDto;
import com.example.sagicoupon.model.Customer;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(@NotNull Customer source){
        return CustomerDto.builder()
                .id(source.getId())
                .userDto(source.getUserDto())
                .address(source.getAddress())
                .amountOfKids(source.getAmountOfKids())
                .birthDate(source.getBirthDate())
                .build();
    }
}
