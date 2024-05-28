package com.example.backend.service;


import com.example.backend.entity.Option;
import com.example.backend.mapper.OptionMapper;
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
public class OptionServiceImplTest {

    @Mock
    private OptionMapper optionMapper;

    @InjectMocks
    private OptionServiceImpl optionServiceImpl;

    @Test
    public void testAddOption() {
        Option option = new Option(null, "Yes", 1L);
        when(optionMapper.insert(option)).thenReturn(1);
        Option createdOption = optionServiceImpl.addOption(option);

        assertNotNull(createdOption);
        verify(optionMapper).insert(option);
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

