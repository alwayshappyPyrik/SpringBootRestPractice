package com.example.springbootrestpractice;

import com.example.springbootrestpractice.authorization.InvalidCredentials;
import com.example.springbootrestpractice.authorization.UnauthorizedUser;
import com.example.springbootrestpractice.controller.AuthorizationController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SpringBootRestPracticeApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorizationController authorizationController;

    @Test
    public void testGoodInput() throws Exception {
        mvc
                .perform(MockMvcRequestBuilders.get("/authorize?user=yaroslav&password=12345"))
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidCredentials() throws Exception {
        Mockito.when(authorizationController.getAuthorities("", "12345")).thenThrow(new InvalidCredentials("User name or password is empty"));
    }

    @Test
    public void testUnauthorizedUser() throws Exception {
        Mockito.when(authorizationController.getAuthorities("pety", "12345")).thenThrow(new UnauthorizedUser("Unknown user pety"));
    }
}
