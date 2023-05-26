package nextstep.qna.domain;

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
}
