package com.medhead.emergency.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
public class LoginControllerIT {

    @Autowired
    private MockMvc mvc;

    @Container
    private static final MySQLContainer<?> SQL_CONTAINER = new MySQLContainer<>().withDatabaseName("medhead");

    @DynamicPropertySource
    static void configureMysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.driver-class-name", SQL_CONTAINER::getDriverClassName);
        registry.add("spring.datasource.url", SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", SQL_CONTAINER::getPassword);
    }

    @Test
    public void userLoginSuccessIT() throws Exception {
        mvc.perform(get("/login")
                .queryParam("username", "user")
                .queryParam("password", "user"))
                .andExpect(status().isOk());
    }

    @Test
    public void userLoginFailedIT() throws Exception {
        mvc.perform(get("/login")
                .queryParam("username", "user")
                .queryParam("password", "wrongpassword"))
                .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void userPageAccessWithAuthenticatedUserIT() throws Exception {
        mvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void userPageAccessWithUnauthenticatedUserIT() throws Exception {
        mvc.perform(get("/user")).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void adminPageAccessWithAuthenticatedAdminIT() throws Exception {
        mvc.perform(get("/admin")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void adminPageAccessWithAuthenticatedUserIT() throws Exception {
        mvc.perform(get("/admin")).andDo(print()).andExpect(status().isForbidden());
    }

}
