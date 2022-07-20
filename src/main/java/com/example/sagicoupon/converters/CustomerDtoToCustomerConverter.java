package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CustomerDto;
import com.example.sagicoupon.model.Customer;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

    private UserDtoToUserConverter userDtoToUserConverter;

    @Autowired
    public CustomerDtoToCustomerConverter(UserDtoToUserConverter userDtoToUserConverter) {
        this.userDtoToUserConverter = userDtoToUserConverter;
    }

    @Override
    public Customer convert(@NotNull CustomerDto source) {
        Customer customer = new Customer();
        customer.setId(source.getId());
        customer.setUser(userDtoToUserConverter.convert(source.getUserDto()));
        customer.setAddress(source.getAddress());
        customer.setAmountOfKids(source.getAmountOfKids());
        customer.setBirthDate(source.getBirthDate());
        return customer;
    }
}
