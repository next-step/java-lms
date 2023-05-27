package nextstep.qna.domain;

import nextstep.utils.DomainId;

import java.util.Objects;

public class QuestionId implements DomainId {
    private final Long questionId;

    public QuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public Long value() {
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
