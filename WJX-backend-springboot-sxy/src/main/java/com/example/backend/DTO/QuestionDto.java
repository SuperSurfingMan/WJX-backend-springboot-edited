package com.example.backend.DTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.backend.entity.Question}
 */
public class QuestionDto implements Serializable {
    private final Long id;
    private final String text;
    private final String type;
    private final Long surveyId;

    public QuestionDto(Long id, String text, String type, Long surveyId) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.surveyId = surveyId;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto entity = (QuestionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.text, entity.text) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.surveyId, entity.surveyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, type, surveyId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "text = " + text + ", " +
                "type = " + type + ", " +
                "surveyId = " + surveyId + ")";
    }

    public void setSurveyId(Long surveyId) {

    }
}