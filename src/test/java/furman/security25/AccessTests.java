package furman.security25;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
@author   User
@project   security25
@class  AccessTests
@version  1.0.0
@since 29.11.2025 - 22.44
*/


import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void whenAnonymThenStatusUnauthorized() throws Exception {

        mockMvc.perform(get("/api/v1/cats"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/user"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserToUserThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserToAdminThenStatusForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "superadmin",password = "superadmin",roles = {"SUPERADMIN"})  //superadmin у мене заходить всюди
    void whenSuperadminToUserThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "superadmin",password = "superadmin",roles = {"SUPERADMIN"})
    void whenSuperadminToAdminThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user",password = "user",roles = {"USER"})
    void whenUserToUnknownThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin",roles = {"ADMIN"})
    void whenAdminToUnknownThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "123",password = "123",roles = {"QWE"})
    void whenWrongRoleToUnknownThenStatusForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/unknown"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperadminToUnknownThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserToStrangerThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/stranger"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminToStrangerThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/cats/hello/stranger"))
                .andExpect(status().isOk());
    }





}