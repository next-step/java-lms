package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete() {
        for (Answer answer : answers) {
            answer.delete();
        }
    }

    public List<DeleteHistory> deleteHistories() {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : answers) {
            histories.add(answer.deleteHistory());
        }
        return histories;
    }

    public void validateOwner(NsUser owner) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwner(owner);
        }
    }
}
