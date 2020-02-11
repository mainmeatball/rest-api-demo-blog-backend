package helloblog.controller;

import helloblog.security.jwt.JwtFilter;
import helloblog.security.jwt.TokenProvider;
import helloblog.dto.UserDto;
import helloblog.entity.User;
import helloblog.security.SecurityUtils;
import helloblog.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AuthenticationController {



    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    @Autowired
    public AuthenticationController(TokenProvider tokenProvider,
                                    AuthenticationManagerBuilder authenticationManagerBuilder,
                                    UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    public User signUp(@Valid @RequestBody UserDto userDto) {
        if (userService.findByUsername(userDto.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "This username was already used.");
        }
        return userService.save(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody UserDto userDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityUtils.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    // just for a test check which user is logged in
    @GetMapping("/who")
    public String whoAmI() {
        String username = SecurityUtils.getCurrentUsername();
        if (username == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You are not authorized.");
        }
        return username;
    }

    static class JWTToken {
        private String idToken;
        JWTToken(String idToken) {
            this.idToken = idToken;
        }
        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }
        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
