package com.example.backend.controller;

import com.example.backend.DTO.OptionDto;
import com.example.backend.service.OptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.example.backend.config.SecurityConfig;
import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionController extends SecurityConfig{

    @Autowired
    private OptionServiceImpl optionServiceImpl;

    //给某个问题添加选项
    @PostMapping("/{questionId}")
    public ResponseEntity<OptionDto> addOptionToQuestion(@PathVariable Long questionId, @RequestBody OptionDto optionDto) {
        OptionDto createdOption = optionServiceImpl.addOptionToQuestion(questionId, optionDto);
        return ResponseEntity.ok(createdOption);
    }

    //根据选项id获取选项
    @GetMapping("/{questionId}")
    public ResponseEntity<List<OptionDto>> getOptionsForQuestion(@PathVariable Long questionId) {
        List<OptionDto> options = optionServiceImpl.getOptionsForQuestion(questionId);
        return ResponseEntity.ok(options);
    }

    //根据选项id更新选项
    @PutMapping("/{id}")
    public ResponseEntity<OptionDto> updateOption(@PathVariable Long id, @RequestBody OptionDto optionDto) {
        OptionDto updatedOption = optionServiceImpl.updateOption(id, optionDto);
        return ResponseEntity.ok(updatedOption);
    }

    //根据选项id删除选项
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOption(@PathVariable Long id) {
        optionServiceImpl.deleteOption(id);
        return ResponseEntity.ok().build();
    }
}
