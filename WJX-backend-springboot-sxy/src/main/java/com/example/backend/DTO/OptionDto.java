package com.example.backend.DTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.backend.entity.Option}
 */
public class OptionDto implements Serializable {
    private final Long id;
    private final String optionText;
    private final Long questionId;

    public OptionDto(Long id, String optionText, Long questionId) {
        this.id = id;
        this.optionText = optionText;
        this.questionId = questionId;
    }

    public Long getId() {
        return id;
    }

    public String getOptionText() {
        return optionText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionDto entity = (OptionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.optionText, entity.optionText) &&
                Objects.equals(this.questionId, entity.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, optionText, questionId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "optionText = " + optionText + ", " +
                "questionId = " + questionId + ")";
    }

    public void setQuestionId(Long questionId) {
    }

    public void setId(Long optionId) {
    }
}