package com.example.helloblog.controller;

import com.example.helloblog.dto.SignUpDto;
import com.example.helloblog.entity.User;
import com.example.helloblog.service.SecurityService;
import com.example.helloblog.service.UserDetailsServiceImpl;
import com.example.helloblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public User signUp(@RequestBody SignUpDto signUpDto) {
        User user = userService.signUp(signUpDto);
        securityService.autoLogin(signUpDto.getUsername(), signUpDto.getPassword());
        return user;
    }

    // just for a test check which user is logged in
    @GetMapping("/who_am_i")
    public UserDetails whoAmI() {
        return userDetailsService.getCurrentUser();
    }
}
