//package helloblog.rest;
//
//import org.junit.Test;
//import org.springframework.http.MediaType;
//import helloblog.util.AbstractControllerTest;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static helloblog.util.LoginUtils.getTokenForLogin;
//
//public class MessageControllerTest extends AbstractControllerTest {
//
//    @Test
//    public void getMessageForUser() throws Exception {
//        final String token = getTokenForLogin("a", "a", getMockMvc());
//        assertSuccessfulPersonRequest(token);
//    }
//
//    @Test
//    public void getMessageForAdmin() throws Exception {
//        final String token = getTokenForLogin("admin", "admin", getMockMvc());
//        assertSuccessfulPersonRequest(token);
//    }
//
//    @Test
//    public void getMessageForAnonymous() throws Exception {
//        getMockMvc().perform(get("/messages")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized());
//    }
//
//    private void assertSuccessfulPersonRequest(String token) throws Exception {
//        getMockMvc().perform(get("/messages")
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{}"));
//    }
//}