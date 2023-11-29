package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    private DeleteHistories() {}

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories from(List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public void addQuestionDeleteHistory(Question question) {
        DeleteHistory questionHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
        deleteHistories.add(questionHistory);
    }

    public void addAnswerDeleteHistory(Answer answer) {
        DeleteHistory answerHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
        deleteHistories.add(answerHistory);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return this.deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
