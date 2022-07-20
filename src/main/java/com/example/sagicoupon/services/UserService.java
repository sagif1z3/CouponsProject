package com.example.sagicoupon.services;

import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.model.User;
import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getAllUsers();

    User findUserById(Long id);

    User updateUser(User user);

    void deleteUserById(Long id);

    UserDto getById (Long id);
}
