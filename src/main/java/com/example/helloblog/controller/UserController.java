package com.example.helloblog.controller;

import com.example.helloblog.dto.UserDto;
import com.example.helloblog.entity.User;
import com.example.helloblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // TODO: implement autologin feature
    @PostMapping("/sign_up")
    public User signUp(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
}
