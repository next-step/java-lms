package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void checkDelete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkCanDelete(loginUser);
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> makeDelete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.makeDelete(loginUser));
        }
        return deleteHistories;
    }
}
