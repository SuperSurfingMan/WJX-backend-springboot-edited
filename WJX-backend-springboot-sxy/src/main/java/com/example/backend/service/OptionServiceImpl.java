package com.example.backend.service;

import com.example.backend.DTO.OptionDto;
import com.example.backend.entity.Option;
import com.example.backend.entity.OptionExample;
import com.example.backend.mapper.OptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OptionServiceImpl implements OptionServiceInter{
    @Autowired
    private OptionMapper optionMapper;

    public OptionServiceImpl(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }



    // 获取问题的选项
    public List<OptionDto> getOptionsForQuestion(Long questionId) {

        OptionExample example = new OptionExample();
        example.createCriteria().andQuestionIdEqualTo(questionId);
        List<Option> options = optionMapper.selectByExample(example);
        return options.stream().map(option -> new OptionDto(option.getId(), option.getOptionText(), option.getQuestionId())).collect(Collectors.toList());


    }

    // 删除选项
    public void deleteOption(Long optionId) {
        optionMapper.deleteByPrimaryKey(optionId);
    }


    // 添加选项
    public Option addOption(Option option) {
        if (optionMapper.insert(option) > 0) {
            return option; // 假设插入成功后，返回这个option
        }
        return null; // 或者抛出异常，根据你的业务逻辑决定
    }

    // 更新选项
    public OptionDto updateOption(Long id, OptionDto option) {

        Option optionToUpdate = optionMapper.selectByPrimaryKey(id);
        optionToUpdate.setOptionText(option.getOptionText());
        optionMapper.updateByPrimaryKeySelective(optionToUpdate);
        return option;
    }


    // 获取所有选项
    public Iterable<Option> getOptions() {
        return optionMapper.selectByExample(new OptionExample());
    }

    // 添加选项到问题
    public OptionDto addOptionToQuestion(Long questionId, OptionDto optionDto) {

        Option option = new Option();
        option.setQuestionId(questionId);
        option.setOptionText(optionDto.getOptionText());
        option.setId(optionDto.getId());
        optionMapper.insert(option);
        return optionDto;
    }
}
