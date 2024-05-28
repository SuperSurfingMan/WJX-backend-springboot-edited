package com.example.backend.service;

import com.example.backend.entity.Survey;
import com.example.backend.mapper.SurveyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.jdbc.Sql;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceInterTest {

    @Mock
    private SurveyMapper surveyMapper;

    @InjectMocks
    private SurveyServiceInter surveyService;

    @Test
    public void shouldCreateSurvey() {
        Survey survey = new Survey(null, "Employee Engagement", "Yearly engagement survey");
        when(surveyMapper.insert(any(Survey.class))).thenReturn(1);
        surveyService.createSurvey(survey);
        verify(surveyMapper).insert(survey);
    }
    @BeforeTestClass
    @Sql("/sql/data.sql")  // 指定你的 setup.sql 文件的路径
    public void setup() {
        // setup.sql 脚本将在每个测试方法之前执行
    }

    @AfterTestClass
    @Sql("/sql/cleanup.sql")  // 指定你的 cleanup.sql 文件的��径
    public void cleanup() {
        // cleanup.sql 脚本将在每个测试方法之后执行
    }
}
