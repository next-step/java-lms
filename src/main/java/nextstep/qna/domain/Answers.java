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
        this(new ArrayList<>());
    }

    public List<Answer> value() {
        return this.answers;
    }

    public List<Answer> add(Answer answer) {
        answers.add(answer);
        return this.answers;
    }

    public List<DeleteHistory> delete(NsUser nsUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.addAll(answer.delete(nsUser));
        }
        return deleteHistories;
    }
}
