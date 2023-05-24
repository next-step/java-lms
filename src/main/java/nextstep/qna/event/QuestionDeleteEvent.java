package nextstep.qna.event;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class QuestionDeleteEvent {
    private Question question;
    private NsUser loginUser;

    public QuestionDeleteEvent(Question question, NsUser loginUser) {
        this.question = question;
        this.loginUser = loginUser;
    }

    public List<DeleteHistory> deleteHistory() throws CannotDeleteException {
        return question.createDeleteHistories(loginUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDeleteEvent that = (QuestionDeleteEvent) o;
        return Objects.equals(question, that.question) && Objects.equals(loginUser, that.loginUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, loginUser);
    }
}
