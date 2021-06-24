package com.example.diplomapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(wac)
            .apply(springSecurity())
            .build();
    }

    @WithMockUser(value = "user")
    @Test
    public void notEnoughRightsForRegistrationTest() throws Exception {
        mockMvc.perform(get("/registration"))
            .andExpect(status().isForbidden());
    }

    @Test
    public void enoughRightsForRegistrationTest() throws Exception {
        mockMvc.perform(get("/registration").with(httpBasic("admin", "admin")))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void notAuthenticatedRedirectLogin() throws Exception {
        mockMvc.perform(get("/profile"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void correctLoginTest() throws Exception {
        mockMvc.perform(formLogin().user("admin").password("admin"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentialsTest() throws Exception {
        mockMvc.perform(formLogin().user("admin").password("notAdmin"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void addUserTest() throws Exception {
        MockHttpServletRequestBuilder post = post("/registration")
            .param("name", "")
            .param("surname", "")
            .param("patronymic", "")
            .param("username", "")
            .param("password", "")
            .param("passwordConfirm", "")
            .param("email", "")
            .param("department", "")
            .param("qualification", "")
            .param("position", "")
            .param("salary", "")
            .param("isChief", "false");

        mockMvc.perform(post)
            .andExpect(status().is3xxRedirection());
    }
}