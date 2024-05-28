package com.example.backend.service;

import com.example.backend.DTO.OptionDto;
import com.example.backend.entity.Question;
import java.util.List;

public interface QuestionServiceInter {

        Question addQuestion(Question question);
        Question getQuestionById(Long id);
        List<Question> getAllQuestionsBySurveyId(Long surveyId);
        void updateQuestion(Question question);
        void deleteQuestion(Long id);
        List<OptionDto> getOptionsForQuestion(Long questionId);
        OptionDto addOption(OptionDto option);
        OptionDto updateOption(OptionDto option);

}
