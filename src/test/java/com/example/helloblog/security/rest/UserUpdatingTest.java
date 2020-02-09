package com.example.helloblog.security.rest;

import com.example.helloblog.util.AbstractControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.example.helloblog.util.LoginUtils.getTokenForLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserUpdatingTest extends AbstractControllerTest {

    @Test
    public void updateUserRolesWithInvalidRole() throws Exception {
        final String token = getTokenForLogin("admin", "admin", getMockMvc());
        getMockMvc().perform(put("/admin/users/8/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content("{\"names\": [\"USER_ADMIN\", \"USER_JOHN\"] }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserRolesWithEmptyRoleName() throws Exception {
        final String token = getTokenForLogin("admin", "admin", getMockMvc());
        getMockMvc().perform(put("/admin/users/8/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content("{\"names\": [  ] }"))
                .andExpect(status().isBadRequest());
    }
}