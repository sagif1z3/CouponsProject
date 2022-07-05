package com.example.sagicoupon.controllers;

import com.example.sagicoupon.model.User;
import com.example.sagicoupon.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addnew")
    public User addNewUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}/show")
    public User showUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/showall")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/{id}/delete")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
