package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;
    private final DeleteHistories deleteHistories;

    public Answers(DeleteHistories deleteHistories) {
        this.answers = new ArrayList<>();
        this.deleteHistories = deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
    }
}
