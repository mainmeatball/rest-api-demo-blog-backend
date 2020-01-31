package com.example.helloblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Controller {

    @GetMapping("/")
    public String show() {
        return "Hello! Now is " + LocalDateTime.now();
    }
}
