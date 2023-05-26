package nextstep.qna.domain;

import java.util.Objects;

public class QuestionId {
    private final Long questionId;

    public QuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    @Override
    public String toString() {
        return "QuestionId{" +
                "questionId=" + questionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionId other = (QuestionId) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }
}
