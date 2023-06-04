package com.example.demo.web;

import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.enums.StateEnum;
import com.example.demo.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testAdmin, testPublisher, testReader;

    private NewsEntity testNews, testAdminNews;

    @BeforeEach
    void setUp() {
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
        testPublisher = testDataUtils.createTestPublisher("publisher@example.com");
        testReader = testDataUtils.createTestReader("reader@example.com");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanDatabase();
    }

    @Test
    void testDeleteByAnonymousUser_Forbidden() throws Exception {
        mockMvc.perform(delete("/news/{id}", testNews.getId()).
                with(csrf())).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin@example.com",
            roles = {"ADMIN", "PUBLISHER", "READER"})
    void testDeleteByAdmin() throws Exception {
        mockMvc.perform(delete("/news/{id}", testNews.getId()).
                with(csrf())).andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/news/all"));

    }
    @WithMockUser(username = "publisher@example.com", roles = "PUBLISHER")
    @Test
    void testDeleteByPublisher() throws Exception {
        mockMvc.perform(delete("/news/{id}", testNews.getId()).
                with(csrf())).andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/news/all"));
    }

    @WithMockUser(username = "reader@example.com", roles = "READER")
    @Test
    public void testDeleteNotPublisher_Forbidden() throws Exception {
        mockMvc.perform(delete("/news/{id}", testAdminNews.getId()).
                with(csrf())).andExpect(status().isForbidden());
    }

    @WithUserDetails(value = "publisher@example.com", userDetailsServiceBeanName = "testUserDataService")
    @Test
    void testAddNews() throws Exception {
        mockMvc.perform(post("/news/add").
                param("title", "title").
                param("text", "text").
                param("creationData", "01.01.2023").
                param("validFrom", "01.01.2023").
                param("validTo", "02.02.2023").
                param("state", String.valueOf(StateEnum.NEW)).
                param("photoLink", "http://image.com/image").
                with(csrf())).andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/news/all"));
    }
}
