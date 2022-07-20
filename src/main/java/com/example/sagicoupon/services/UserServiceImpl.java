package com.example.sagicoupon.services;

import com.example.sagicoupon.converters.UserDtoToUserConverter;
import com.example.sagicoupon.converters.UserToUserDtoConverter;
import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.model.User;
import com.example.sagicoupon.repositories.UserRepository;
import com.example.sagicoupon.validators.UserValidators;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserValidators userValidators;
    private UserToUserDtoConverter userToUserDtoConverter;
    private UserDtoToUserConverter userDtoToUserConverter;

    @Override
    public User addUser(User user) {
        return Optional.ofNullable(user)
                .filter(userValidators::addUserValidations)
                .map(userToUserDtoConverter::convert)
                .map(userRepository::save)
                .map(userDtoToUserConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save user"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoToUserConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .map(userDtoToUserConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find user by id"));
    }

    @Override
    public User updateUser(User user) {
        return Optional.ofNullable(user)
                .filter(userValidators::updateUserValidation)
                .map(userToUserDtoConverter::convert)
                .map(userRepository::save)
                .map(userDtoToUserConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update user"));
    }

    @Override
    public void deleteUserById(Long id) {
        Optional.ofNullable(findUserById(id))
                .map(userToUserDtoConverter::convert)
                .ifPresent(userRepository::delete);
    }

    @Override
    public UserDto getById(Long id){
        return userRepository.findById(id).get();
    }
}
