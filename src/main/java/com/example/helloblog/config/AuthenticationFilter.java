package com.example.helloblog.config;

import com.example.helloblog.dto.LoginDto;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginDto loginRequest = this.getLoginRequest(request);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private LoginDto getLoginRequest(HttpServletRequest request) {
        BufferedReader reader = null;
        LoginDto loginRequest = null;
        try {
            reader = request.getReader();
            Gson gson = new Gson();
            loginRequest = gson.fromJson(reader, LoginDto.class);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (loginRequest == null) {
            loginRequest = new LoginDto();
        }
        return loginRequest;
    }
}