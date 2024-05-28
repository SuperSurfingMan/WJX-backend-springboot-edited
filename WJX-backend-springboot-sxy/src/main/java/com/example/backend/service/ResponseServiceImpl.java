package com.example.backend.service;

import com.example.backend.DTO.ResponseDto;
import com.example.backend.entity.Response;
import com.example.backend.entity.ResponseExample;
import com.example.backend.mapper.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseServiceImpl implements ResponseServiceInter{
    @Autowired
    private ResponseMapper responseMapper;

    public Response saveResponse(Response response) {
        responseMapper.insert(response);
        return response;
    }

    public List<ResponseDto> getResponsesForQuestion(Long questionId) {
        ResponseExample example = new ResponseExample();
        example.createCriteria().andQuestionIdEqualTo(questionId);
        List<Response> responses = responseMapper.selectByExample(example);
        return responses.stream().map(response -> new ResponseDto(response.getId(), response.getQuestionId(),response.getAnswerText())).collect(Collectors.toList());

    }

    public ResponseDto addResponseToQuestion(Long questionId, ResponseDto responseDto) {

        Response response = new Response();
        response.setQuestionId(questionId);
        response.setAnswerText(responseDto.getAnswerText());
        response.setId(responseDto.getId());
        responseMapper.insert(response);
        return responseDto;}

    public void deleteResponse(Long id) {
        responseMapper.deleteByPrimaryKey(id);

    }
}
