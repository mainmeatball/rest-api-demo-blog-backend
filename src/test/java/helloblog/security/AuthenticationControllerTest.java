package helloblog.security;

import helloblog.util.AbstractControllerTest;

import org.junit.Test;
import org.springframework.http.MediaType;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends AbstractControllerTest {

    @Test
    public void successfulAuthenticationWithUser() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"a\", \"username\": \"a\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id_token")));
    }

    @Test
    public void successfulAuthenticationWithAdmin() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"admin\", \"username\": \"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id_token")));
    }

    @Test
    public void unsuccessfulAuthenticationWithWrongPassword() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"a\", \"username\": \"b\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(not(containsString("id_token"))));
    }

    @Test
    public void unsuccessfulAuthenticationWithNotExistingUser() throws Exception {
        getMockMvc().perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"any_password\", \"username\": \"not_existing\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(not(containsString("id_token"))));
    }
}
