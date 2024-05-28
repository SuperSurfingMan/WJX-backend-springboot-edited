package com.example.backend.DTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.backend.entity.Response}
 */

public class ResponseDto implements Serializable {
    private final Long id;
    private final Long questionId;
    private final String answerText;

    public ResponseDto(Long id, Long questionId, String answerText) {
        this.id = id;
        this.questionId =questionId;
        this.answerText = String.valueOf(answerText);

    }

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDto entity = (ResponseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.answerText, entity.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, answerText);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "questionId = " + questionId + ", " +
                "answerText = " + answerText + ")";
    }
}