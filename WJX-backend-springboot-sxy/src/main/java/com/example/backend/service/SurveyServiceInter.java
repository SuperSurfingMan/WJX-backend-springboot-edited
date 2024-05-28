package com.example.backend.service;

import com.example.backend.DTO.OptionDto;
import com.example.backend.DTO.QuestionDto;
import com.example.backend.DTO.SurveyDto;

import java.util.List;

public interface SurveyServiceInter {
    SurveyDto createSurvey(SurveyDto surveyDto);
    List<SurveyDto> getAllSurveys();
    SurveyDto getSurveyById(Long id);
    SurveyDto updateSurvey(Long id, SurveyDto surveyDto);
    void deleteSurvey(Long id);
    void addQuestionToSurvey(Long surveyId, QuestionDto questionDto);
    List<QuestionDto> getQuestionsForSurvey(Long surveyId);
    List<OptionDto> getOptionsForQuestion(Long questionId);
}
