package com.example.helloblog.service;

import com.example.helloblog.dto.SignUpDto;
import com.example.helloblog.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User signUp(SignUpDto signUpDto);

    User findByUsername(String username);

}
