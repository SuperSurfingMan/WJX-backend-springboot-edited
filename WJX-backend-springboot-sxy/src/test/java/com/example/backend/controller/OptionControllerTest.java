package com.example.backend.controller;

import com.example.backend.DTO.OptionDto;
import com.example.backend.service.OptionServiceImpl;
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

import java.util.Arrays;

@WebMvcTest(OptionController.class)
@WithMockUser(username ="11",password = "11",roles = {"ADMIN"})
public class OptionControllerTest  {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OptionServiceImpl optionServiceImpl;

    private OptionDto optionDto;

    @BeforeEach
    @Sql("classpath:/sql/data.sql")
    public void setup() {
        optionDto = new OptionDto(10L, "Option 1", 1L);
    }

    @AfterEach
    @Sql("classpath:/sql/cleanup.sql")
    public void cleanup() {
        // cleanup.sql script will run after each test method
    }

    @Test
    public void testAddOptionToQuestion() throws Exception {
        Mockito.when(optionServiceImpl.addOptionToQuestion(Mockito.anyLong(), Mockito.any(OptionDto.class))).thenReturn(optionDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/options/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":10,\"questionId\":1,\"optionText\":\"Option 1\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.optionText").value("Option 1"));
    }

    @Test
    public void testGetOptionsForQuestion() throws Exception {
        Mockito.when(optionServiceImpl.getOptionsForQuestion(1L)).thenReturn(Arrays.asList(optionDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/options/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].optionText").value("Option 1"));
    }

    @Test
    public void testUpdateOption() throws Exception {
        Mockito.when(optionServiceImpl.updateOption(Mockito.anyLong(), Mockito.any(OptionDto.class))).thenReturn(optionDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/options/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"optionText\":\"Option 1\",\"questionId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.optionText").value("Option 1"));
    }

    @Test
    public void testDeleteOption() throws Exception {
        Mockito.doNothing().when(optionServiceImpl).deleteOption(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/options/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}