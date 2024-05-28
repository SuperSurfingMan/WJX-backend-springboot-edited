package com.example.backend.controller;

import com.example.backend.DTO.ResponseDto;
import com.example.backend.service.ResponseServiceImpl;
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


@WebMvcTest(ResponseController.class)
public class ResponseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResponseServiceImpl responseServiceImpl;

    private ResponseDto responseDto;

    @BeforeEach
    @Sql("classpath:/sql/data.sql")
    public void setup() {
        responseDto = new ResponseDto(1L,1L , "Response 1");
    }
    @AfterEach
    @Sql("classpath:/sql/cleanup.sql")
    public void cleanup() {
        // cleanup.sql script will run after each test method
    }

    @Test
    public void testAddResponseToQuestion() throws Exception {
        Mockito.when(responseServiceImpl.addResponseToQuestion(Mockito.anyLong(), Mockito.any(ResponseDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/responses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"question_id\":1,\"answerText\":\"Response 1\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.answerText").value("Response 1"));
    }

    @Test
    public void testGetResponsesForQuestion() throws Exception {
        Mockito.when(responseServiceImpl.getResponsesForQuestion(1L)).thenReturn(Collections.singletonList(responseDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/responses/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].answerText").value("Response 1"));
    }

    @Test
    public void testDeleteResponse() throws Exception {
        Mockito.doNothing().when(responseServiceImpl).deleteResponse(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/responses/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}