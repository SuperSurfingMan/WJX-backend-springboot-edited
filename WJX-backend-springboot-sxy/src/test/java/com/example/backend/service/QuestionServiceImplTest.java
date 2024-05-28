package com.example.backend.service;

import com.example.backend.entity.Question;
import com.example.backend.mapper.QuestionMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionMapper questionMapper;

    @InjectMocks
    private QuestionServiceImpl questionServiceImpl;

    @Test
    public void testAddQuestion() {
        Question question = new Question(null, "What is your age?", "TEXT", 1L);
        when(questionMapper.insert(question)).thenReturn(1);
        Question createdQuestion = questionServiceImpl.addQuestion(question);

        assertNotNull(createdQuestion);
        verify(questionMapper).insert(question);
    }

    @BeforeEach
    @Sql("/sql/data.sql")  // 指定你的 setup.sql 文件的路径
    public void setup() {
        // setup.sql 脚本将在每个测试方法之前执行
    }

    @AfterEach
    @Sql("/sql/cleanup.sql")  // 指定你的 cleanup.sql 文件的��径
    public void cleanup() {
        // cleanup.sql 脚本将在每个测试方法之后执行
    }
}
