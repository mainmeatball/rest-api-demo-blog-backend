package com.example.helloblog.controller;

import com.example.helloblog.dto.UserDto;
import com.example.helloblog.entity.User;
import com.example.helloblog.service.SecurityService;
import com.example.helloblog.service.UserDetailsServiceImpl;
import com.example.helloblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/sign_up")
    public User signUp(@RequestBody UserDto userDto) {
        User user = userService.save(userDto);
        securityService.autoLogin(userDto.getUsername(), userDto.getPassword());
        return user;
    }
}
