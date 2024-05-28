package com.example.backend.service;

import com.example.backend.DTO.ResponseDto;
import com.example.backend.entity.Response;

import java.util.List;

public interface ResponseServiceInter {
    Response saveResponse(Response response);
    List<ResponseDto> getResponsesForQuestion(Long questionId);
    ResponseDto addResponseToQuestion(Long questionId, ResponseDto responseDto);
    void deleteResponse(Long id);
}