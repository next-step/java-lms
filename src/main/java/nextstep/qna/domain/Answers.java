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

    public void validateOwner(NsUser owner) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwner(owner);
        }
    }

    public List<DeleteHistory> delete(NsUser owner) throws CannotDeleteException {
        validateOwner(owner);
        return deleteAll();
    }

    public List<DeleteHistory> deleteAll() {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : answers) {
            histories.add(answer.delete());
        }
        return histories;
    }
}
