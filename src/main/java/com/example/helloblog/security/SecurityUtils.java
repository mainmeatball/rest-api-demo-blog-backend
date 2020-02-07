package com.example.helloblog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityUtils.class);

    private SecurityUtils() {
    }

    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            LOG.debug("no authentication in security context found");
            return "";
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }

        LOG.debug("found username '{}' in security context", username);

        return username;
    }
}