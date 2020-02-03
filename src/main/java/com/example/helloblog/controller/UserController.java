package com.example.helloblog.controller;

import com.example.helloblog.dto.SignUpDto;
import com.example.helloblog.entity.User;
import com.example.helloblog.service.SecurityService;
import com.example.helloblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/sign_up")
    public User signUp(@RequestBody SignUpDto signUpDto) {
        User user = userService.signUp(signUpDto);
        securityService.autoLogin(signUpDto.getUsername(), signUpDto.getPassword());
        return user;
    }
}
