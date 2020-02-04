package com.example.helloblog.service;

import com.example.helloblog.dto.SignUpDto;
import com.example.helloblog.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll(int pageNo, int pageSize, String sortBy, String dir);

    User signUp(SignUpDto signUpDto);

    User findByUsername(String username);

}
