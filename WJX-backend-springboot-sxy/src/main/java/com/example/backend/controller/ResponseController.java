package com.example.backend.controller;

import com.example.backend.DTO.ResponseDto;
import com.example.backend.config.SecurityConfig;
import com.example.backend.service.ResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController extends SecurityConfig {

    @Autowired
    private ResponseServiceImpl responseServiceImpl;

    //给某个问题添加回复
    @PostMapping("/{questionId}")
    public ResponseEntity<ResponseDto> addResponseToQuestion(@PathVariable Long questionId, @RequestBody ResponseDto responseDto) {
        ResponseDto createdResponse = responseServiceImpl.addResponseToQuestion(questionId, responseDto);
        return ResponseEntity.ok(createdResponse);
    }

    //根据问题id获取回复
    @GetMapping("/{questionId}")
    public ResponseEntity<List<ResponseDto>> getResponsesForQuestion(@PathVariable Long questionId) {
        List<ResponseDto> responses = responseServiceImpl.getResponsesForQuestion(questionId);
        return ResponseEntity.ok(responses);
    }

    //根据回复id删除回复
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResponse(@PathVariable Long id) {
        responseServiceImpl.deleteResponse(id);
        return ResponseEntity.ok().build();
    }
}
