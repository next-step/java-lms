package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public boolean add(Answer answer) {
        return answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            DeleteHistory history = answer.delete(loginUser);
            deleteHistories.add(history);
        }
        return deleteHistories;
    }
}
