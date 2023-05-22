package nextstep.qna.event;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import org.springframework.context.ApplicationEvent;

import java.util.List;

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
}
