package com.example.sagicoupon.controllers;

import com.example.sagicoupon.model.User;
import com.example.sagicoupon.model.UserLogin;
import com.example.sagicoupon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public User login (@RequestBody UserLogin userLogin){
        return userService.login(userLogin);
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
