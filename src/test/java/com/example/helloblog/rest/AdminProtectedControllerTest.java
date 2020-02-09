package com.example.helloblog.rest;

import org.junit.Test;
import org.springframework.http.MediaType;
import com.example.helloblog.util.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static com.example.helloblog.util.LoginUtils.getTokenForLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminProtectedControllerTest extends AbstractControllerTest {

    @Test
    public void getAdminProtectedEndpointForUser() throws Exception {
        final String token = getTokenForLogin("a", "a", getMockMvc());

        getMockMvc().perform(get("/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAdminProtectedEndpointForAdmin() throws Exception {
        final String token = getTokenForLogin("admin", "admin", getMockMvc());

        getMockMvc().perform(get("/admin/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"username\" : \"a\"\n" +
                                "}"
                ));
    }

    @Test
    public void getAdminProtectedEndpointForAnonymous() throws Exception {
        getMockMvc().perform(get("/admin/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
