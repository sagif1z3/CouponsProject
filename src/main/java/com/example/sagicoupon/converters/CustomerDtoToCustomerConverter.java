package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CustomerDto;
import com.example.sagicoupon.model.Customer;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(@NotNull CustomerDto source) {
        Customer customerDto = new Customer();
        customerDto.setId(source.getId());
        customerDto.setUserDto(source.getUserDto());
        customerDto.setAddress(source.getAddress());
        customerDto.setAmountOfKids(source.getAmountOfKids());
        customerDto.setBirthDate(source.getBirthDate());
        return customerDto;
    }
}
