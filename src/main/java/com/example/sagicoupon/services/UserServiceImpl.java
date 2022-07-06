package com.example.sagicoupon.services;

import com.example.sagicoupon.converters.UserDtoToUserConverter;
import com.example.sagicoupon.converters.UserToUserDtoConverter;
import com.example.sagicoupon.model.User;
import com.example.sagicoupon.repositories.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserToUserDtoConverter userToUserDtoConverter;
    private UserDtoToUserConverter userDtoToUserConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           @Lazy UserToUserDtoConverter userToUserDtoConverter,
                           @Lazy UserDtoToUserConverter userDtoToUserConverter) {
        this.userRepository = userRepository;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
    }

    public User addUser(User user) {
        return Optional.ofNullable(userToUserDtoConverter.convert(user))
                .map(userRepository::save)
                .map(userDtoToUserConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save user"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoToUserConverter::convert)
                .collect(Collectors.toList());
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .map(userDtoToUserConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find user by id"));
    }

    public User updateUser(User user) {
        User existingUser = null;
        try {
            existingUser = findUserById(user.getId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not update user because user not found ");
        }
        return Optional.ofNullable(userToUserDtoConverter.convert(existingUser))
                .map(userRepository::save)
                .map(userDtoToUserConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update user"));
    }

    public void deleteUserById(Long id) {
        Optional.ofNullable(userToUserDtoConverter.convert(findUserById(id)))
                .ifPresent(userRepository::delete);
    }
}
