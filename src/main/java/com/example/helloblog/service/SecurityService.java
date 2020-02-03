package com.example.helloblog.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
