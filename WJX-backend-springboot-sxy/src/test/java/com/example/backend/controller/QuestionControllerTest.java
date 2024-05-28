package com.example.backend.controller;

import com.example.backend.DTO.OptionDto;
import com.example.backend.entity.Question;
import com.example.backend.service.QuestionServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(QuestionController.class)
@WithMockUser(username ="11",password = "",roles = {"ADMIN"})
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionServiceImpl questionServiceImpl;

    private Question question;
    private OptionDto optionDto;

    @BeforeEach
    @Sql("classpath:/sql/data.sql")
    public void setup() {
        question = new Question(1L, "What is your age?", "TEXT", 1L);
        optionDto = new OptionDto(1L, "Option 1", 1L);
    }

    @AfterEach
    @Sql("classpath:/sql/cleanup.sql")
    public void cleanup() {
        // cleanup.sql script will run after each test method
    }

    @Test
    public void testAddQuestion() throws Exception {
        Mockito.when(questionServiceImpl.addQuestion(Mockito.any(Question.class))).thenReturn(question);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/surveys/1/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"What is your age?\",\"type\":\"TEXT\",\"surveyId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("What is your age?"));
    }

    @Test
    public void testGetQuestionById() throws Exception {
        Mockito.when(questionServiceImpl.getQuestionById(1L)).thenReturn(question);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/surveys/1/questions/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("What is your age?"));
    }

    @Test
    public void testGetAllQuestionsBySurveyId() throws Exception {
        Mockito.when(questionServiceImpl.getAllQuestionsBySurveyId(1L)).thenReturn(Collections.singletonList(question));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/surveys/1/questions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].text").value("What is your age?"));
    }

    @Test
    public void testUpdateQuestion() throws Exception {
        Mockito.doNothing().when(questionServiceImpl).updateQuestion(Mockito.any(Question.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/surveys/1/questions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"What is your age?\",\"type\":\"TEXT\",\"surveyId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("What is your age?"));
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        Mockito.doNothing().when(questionServiceImpl).deleteQuestion(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/surveys/1/questions/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetOptionsForQuestion() throws Exception {
        Mockito.when(questionServiceImpl.getOptionsForQuestion(1L)).thenReturn(Collections.singletonList(optionDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/surveys/1/questions/1/options"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].optionText").value("Option 1"));
    }

    @Test
    public void testAddOption() throws Exception {
        Mockito.when(questionServiceImpl.addOption(Mockito.any(OptionDto.class))).thenReturn(optionDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/surveys/1/questions/1/options")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"optionText\":\"Option 1\",\"questionId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.optionText").value("Option 1"));
    }

    @Test
    public void testUpdateOption() throws Exception {
        Mockito.when(questionServiceImpl.updateOption(Mockito.any(OptionDto.class))).thenReturn(optionDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/surveys/1/questions/1/options/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"optionText\":\"Option 1\",\"questionId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.optionText").value("Option 1"));
    }
}