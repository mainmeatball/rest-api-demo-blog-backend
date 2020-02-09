package com.example.helloblog.security.rest;

import com.example.helloblog.util.AbstractControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ValidationTest extends AbstractControllerTest {

    @Test
    public void registrationWithEmptyUsernameAndPassword() throws Exception {
        getMockMvc().perform(post("/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registrationWithInvalidUsername() throws Exception {
        getMockMvc().perform(post("/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"u53rnA'>3\", \"password\": \"password\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registrationWithInvalidPassword() throws Exception {
        getMockMvc().perform(post("/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"username\", \"password\": \"pa55w0[d\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithEmptyUsernameAndPassword() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithInvalidUsername() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"kjhasd'}\", \"password\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithInvalidPassword() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"{123}\"}"))
                .andExpect(status().isBadRequest());
    }
}
