package com.example.sagicoupon.services;

import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.model.User;
import com.example.sagicoupon.model.UserLogin;

import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getAllUsers();

    User findUserById(long id);

    User updateUser(User user);

    void deleteUserById(long id);

    UserDto getById (long id);

    UserDto getUserByUserName (String userName);

    User login(UserLogin userLogin);
}
