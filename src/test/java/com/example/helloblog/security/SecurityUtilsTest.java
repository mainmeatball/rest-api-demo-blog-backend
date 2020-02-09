package com.example.helloblog.security;

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;

public class SecurityUtilsTest {

    @Test
    public void getCurrentUsername() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
        SecurityContextHolder.setContext(securityContext);
        assertThat(SecurityUtils.getCurrentUsername()).isEqualTo("admin");
    }

    @Test
    public void getCurrentUsernameForNoAuthenticationInContext() {
        assertThat(SecurityUtils.getCurrentUsername()).isNull();
    }
}