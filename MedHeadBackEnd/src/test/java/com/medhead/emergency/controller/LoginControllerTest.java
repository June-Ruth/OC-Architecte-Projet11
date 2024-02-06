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
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
public class LoginControllerTest {

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
    public void userLoginSuccessTest() throws Exception {
        mvc.perform(get("/login")
                .queryParam("username", "user")
                .queryParam("password", "user"))
                .andExpect(status().isOk());
    }

    @Test
    public void userLoginFailedTest() throws Exception {
        mvc.perform(get("/login")
                .queryParam("username", "user")
                .queryParam("password", "wrongpassword"))
                .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void userPageAccessWithAuthenticatedUserTest() throws Exception {
        mvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void userPageAccessWithUnauthenticatedUserTest() throws Exception {
        mvc.perform(get("/user")).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void adminPageAccessWithAuthenticatedAdminTest() throws Exception {
        mvc.perform(get("/admin")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void adminPageAccessWithAuthenticatedUserTest() throws Exception {
        mvc.perform(get("/admin")).andDo(print()).andExpect(status().isForbidden());
    }

}
