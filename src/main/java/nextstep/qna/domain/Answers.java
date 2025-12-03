package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteBy(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deleteBy(user);
        }
    }

    public List<DeleteHistory> toDeleteHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.toDeleteHistory());
        }
        return deleteHistories;
    }
}
