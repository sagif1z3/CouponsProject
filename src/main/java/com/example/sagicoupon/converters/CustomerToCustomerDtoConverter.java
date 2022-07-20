package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.CustomerDto;
import com.example.sagicoupon.model.Customer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto> {

    private final UserToUserDtoConverter userToUserDtoConverter;

    @Autowired
    public CustomerToCustomerDtoConverter(UserToUserDtoConverter userToUserDtoConverter) {
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public CustomerDto convertSave(@NotNull Customer source){
        return CustomerDto.builder()
                .userDto(userToUserDtoConverter.convert(source.getUser()))
                .address(source.getAddress())
                .amountOfKids(source.getAmountOfKids())
                .birthDate(source.getBirthDate())
                .build();
    }

    public CustomerDto convertUpdate(@NotNull Customer source){
        return CustomerDto.builder()
                .id(source.getId())
                .userDto(userToUserDtoConverter.convert(source.getUser()))
                .address(source.getAddress())
                .amountOfKids(source.getAmountOfKids())
                .birthDate(source.getBirthDate())
                .build();
    }

    @Override
    public CustomerDto convert(Customer source) {
        return null;
    }
}
