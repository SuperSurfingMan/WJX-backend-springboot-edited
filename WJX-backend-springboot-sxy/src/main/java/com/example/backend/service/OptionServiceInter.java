package com.example.backend.service;

import com.example.backend.DTO.OptionDto;
import com.example.backend.entity.Option;

import java.util.List;

public interface OptionServiceInter {
    List<OptionDto> getOptionsForQuestion(Long questionId);
    void deleteOption(Long optionId);
    Option addOption(Option option);
    OptionDto updateOption(Long id, OptionDto option);
    Iterable<Option> getOptions();
    OptionDto addOptionToQuestion(Long questionId, OptionDto optionDto);
}