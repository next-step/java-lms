package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    boolean contains(Answer answer) {
        return answers.contains(answer);
    }

    public void ensureAllAnswersOwnedByUser(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.isNotByUser(loginUser);
        }
    }

    public void deleteHistories(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            answer.delete();
            deleteHistories.addHistory(ContentType.ANSWER, answer.getId(), answer.getWriter());
        }
    }
}
