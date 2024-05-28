package com.example.backend.controller;


import com.example.backend.DTO.OptionDto;
import com.example.backend.config.SecurityConfig;
import com.example.backend.entity.Question;
import com.example.backend.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys/{surveyId}/questions")
public class QuestionController extends SecurityConfig {
    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    //给某个问卷添加问题
    @PostMapping
    public ResponseEntity<Question> addQuestion(@PathVariable Long surveyId, @RequestBody Question question) {
        question.setSurveyId(surveyId);
        Question createdQuestion = questionServiceImpl.addQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    //根据问题id获取问题
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {
        Question question = questionServiceImpl.getQuestionById(questionId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    //根据问卷id获取所有问题
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestionsBySurveyId(@PathVariable Long surveyId) {
        List<Question> questions = questionServiceImpl.getAllQuestionsBySurveyId(surveyId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    //根据问题id更新问题
    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long questionId, @RequestBody Question question) {
        question.setId(questionId);
        questionServiceImpl.updateQuestion(question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    //根据问题id删除问题
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionServiceImpl.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //根据问题id获取问题的选项
    @GetMapping("/{questionId}/options")
    public ResponseEntity<List<OptionDto>> getOptionsForQuestion(@PathVariable Long questionId) {
        List<OptionDto> options = questionServiceImpl.getOptionsForQuestion(questionId);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    //给问题添加选项
    @PostMapping("/{questionId}/options")
    public ResponseEntity<OptionDto> addOption(@PathVariable Long questionId, @RequestBody OptionDto option) {
        option.setQuestionId(questionId);
        OptionDto newOption = questionServiceImpl.addOption(option);
        return new ResponseEntity<>(newOption, HttpStatus.CREATED);
    }

    //根据问题id和选项id更新选项
    @PutMapping("/{questionId}/options/{optionId}")
    public ResponseEntity<OptionDto> updateOption(@PathVariable Long questionId, @PathVariable Long optionId, @RequestBody OptionDto option) {
        option.setQuestionId(questionId);
        option.setId(optionId);
        OptionDto updatedOption = questionServiceImpl.updateOption(option);
        return new ResponseEntity<>(updatedOption, HttpStatus.OK);
    }




}
