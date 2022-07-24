package com.example.sagicoupon.services;

import com.example.sagicoupon.converters.UserDtoToUserConverter;
import com.example.sagicoupon.converters.UserToUserDtoConverter;
import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.enums.ErrorType;
import com.example.sagicoupon.exceptions.ServerException;
import com.example.sagicoupon.model.User;
import com.example.sagicoupon.model.UserLogin;
import com.example.sagicoupon.repositories.UserRepository;
import com.example.sagicoupon.security.UserPrincipal;
import com.example.sagicoupon.security.jwt.JwtProvider;
import com.example.sagicoupon.validators.UserValidators;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private JwtProvider jwtProvider;
    private UserRepository userRepository;
    private UserValidators userValidators;
    private AuthenticationManager authenticationManager;
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
    public User findUserById(long id) {
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
    public void deleteUserById(long id) {
        Optional.ofNullable(findUserById(id))
                .map(userToUserDtoConverter::convert)
                .ifPresent(userRepository::delete);
    }

    @Override
    public UserDto getById(long id){
        return userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        return userRepository.findFirstByUsername(userName).orElse(null);
    }

    @Override
    public User login(UserLogin userLogin) {
        if (!userRepository.existsByUsername(userLogin.getUsername())){
            throw new ServerException(ErrorType.SQL_ERROR, "SDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        UserDto userDto = userPrincipal.getUserDto();
        userDto.setToken(jwt);

        return userDtoToUserConverter.convert(userDto);
    }

//    TODO-CHANGE TO FUNCTIONAL
}
