package com.example.backend.controller;

import com.example.backend.DTO.SurveyDto;
import com.example.backend.service.SurveyServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyServiceImpl surveyService;

    private SurveyDto surveyDto;

    @BeforeEach
    @Sql(scripts = "classpath:/sql/data.sql")
    public void setup() {
        surveyDto = new SurveyDto(1L, "Test Survey", 1, "Test Description");
    }
    @AfterEach
    @Sql(scripts = "classpath:/sql/cleanup.sql")
    public void cleanup() {
        // cleanup.sql script will run after each test method
    }

    @Test
    public void testCreateSurvey() throws Exception {
        Mockito.when(surveyService.createSurvey(Mockito.any(SurveyDto.class))).thenReturn(surveyDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/surveys")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Test Survey\",\"createdBy\":1,\"description\":\"Test Description\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Survey"));
    }

    @Test
    public void testGetAllSurveys() throws Exception {
        Mockito.when(surveyService.getAllSurveys()).thenReturn(Collections.singletonList(surveyDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/surveys"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Survey"));
    }

    @Test
    public void testGetSurveyById() throws Exception {
        Mockito.when(surveyService.getSurveyById(1L)).thenReturn(surveyDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/surveys/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Survey"));
    }

    @Test
    public void testUpdateSurvey() throws Exception {
        Mockito.when(surveyService.updateSurvey(Mockito.anyLong(), Mockito.any(SurveyDto.class))).thenReturn(surveyDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/surveys/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Test Survey\",\"createdBy\":1,\"description\":\"Test Description\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Survey"));
    }

    @Test
    public void testDeleteSurvey() throws Exception {
        Mockito.doNothing().when(surveyService).deleteSurvey(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/surveys/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}