package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public DeletedHistories delete(NsUser loginUser) throws CannotDeleteException {
        Set<DeleteHistory> deleteHistories = new HashSet<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return new DeletedHistories(deleteHistories);
    }
}
