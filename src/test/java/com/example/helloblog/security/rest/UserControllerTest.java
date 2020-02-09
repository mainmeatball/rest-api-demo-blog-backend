package com.example.helloblog.security.rest;

import com.example.helloblog.util.AbstractControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.example.helloblog.util.LoginUtils.getTokenForLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest {

    @Before
    public void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void getActualUserForUserWithToken() throws Exception {
        final String token = getTokenForLogin("a", "a", getMockMvc());

        getMockMvc().perform(get("/who")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "a"
                ));
    }

    @Test
    public void getActualUserForUserWithoutToken() throws Exception {
        getMockMvc().perform(get("/who")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}