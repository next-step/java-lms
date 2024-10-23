package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(List<DeleteHistory> deleteHistories, NsUser user) throws CannotDeleteException {
        canDelete(user);
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(
                    new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    private void canDelete(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.canDelete(user);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
