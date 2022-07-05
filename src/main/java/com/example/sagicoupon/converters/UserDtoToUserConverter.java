package com.example.sagicoupon.converters;

import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.model.User;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(@NotNull UserDto source) {
        User userCommand = new User();
        userCommand.setId(source.getId());
        userCommand.setFirstName(source.getFirstName());
        userCommand.setLastName(source.getLastName());
        userCommand.setUsername(source.getUsername());
        userCommand.setPassword(source.getPassword());
        userCommand.setUserType(source.getUserType());
        return userCommand;
    }
}
