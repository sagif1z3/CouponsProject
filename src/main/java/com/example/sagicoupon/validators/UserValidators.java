package com.example.sagicoupon.validators;

import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.enums.ErrorType;
import com.example.sagicoupon.exceptions.ServerException;
import com.example.sagicoupon.model.User;
import com.example.sagicoupon.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserValidators {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public boolean addUserValidations(User user) {

        userRepository.findFirstByUsername(user.getUsername())
                .ifPresent(username -> {
                    throw new ServerException(ErrorType.USER_ALREADY_EXIST);
                });
        Optional.of(user)
                .map(User::getFirstName)
                .filter(Regex::nameValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_FIRST_NAME_IS_INVALID));

        Optional.of(user)
                .map(User::getLastName)
                .filter(Regex::nameValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_LAST_NAME_IS_INVALID));

        Optional.of(user)
                .map(User::getUsername)
                .filter(Regex::emailValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_USERNAME_IS_INVALID));

        Optional.of(user)
                .map(User::getPassword)
                .filter(Regex::passwordValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_PASSWORD_IS_INVALID));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return true;
    }

    public boolean updateUserValidation(User user) {

        Optional<UserDto> find = userRepository.findById(user.getId());
        find.ifPresentOrElse(
                (UserDto) -> {
                    System.out.println(
                            "Found user: " + UserDto);
                },
                () -> {
                    throw new ServerException(ErrorType.USER_DOES_NOT_EXIST);
                });

        userRepository.findFirstByUsername(user.getUsername())
                .ifPresent(username -> {
                    throw new ServerException(ErrorType.USER_ALREADY_EXIST);
                });

        Optional.of(user)
                .map(User::getFirstName)
                .filter(Regex::nameValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_FIRST_NAME_IS_INVALID));

        Optional.of(user)
                .map(User::getLastName)
                .filter(Regex::nameValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_LAST_NAME_IS_INVALID));

        Optional.of(user)
                .map(User::getUsername)
                .filter(Regex::emailValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_USERNAME_IS_INVALID));

        Optional.of(user)
                .map(User::getPassword)
                .filter(Regex::passwordValidation)
                .orElseThrow(() -> new ServerException(ErrorType.USER_PASSWORD_IS_INVALID));

        return true;
    }
}
