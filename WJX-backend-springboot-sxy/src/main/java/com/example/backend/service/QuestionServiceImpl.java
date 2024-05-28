package com.example.backend.service;


import com.example.backend.DTO.OptionDto;
import com.example.backend.entity.Question;
import com.example.backend.entity.QuestionExample;
import com.example.backend.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionServiceInter{
    @Autowired
    private QuestionMapper questionMapper;

    public Question addQuestion(Question question) {
        questionMapper.insert(question);
        return question;
    }

    public Question getQuestionById(Long id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    public List<Question> getAllQuestionsBySurveyId(Long surveyId) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andSurveyIdEqualTo(surveyId);
        return questionMapper.selectByExample(example);
    }

    public void updateQuestion(Question question) {
        questionMapper.updateByPrimaryKey(question);
    }

    public void deleteQuestion(Long id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    public List<OptionDto> getOptionsForQuestion(Long questionId) {
        return questionMapper.getOptionsForQuestion(questionId);
    }

    public OptionDto addOption(OptionDto option) {


        return option;
    }

    public OptionDto updateOption(OptionDto option) {
        return option;
    }
}
