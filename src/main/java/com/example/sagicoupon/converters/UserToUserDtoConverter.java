package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.model.User;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(@NotNull User source){
        return UserDto.builder()
//                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .password(source.getPassword())
                .userType(source.getUserType())
                .token(source.getToken())
                .build();
    }
}
