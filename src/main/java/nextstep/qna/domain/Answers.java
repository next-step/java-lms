package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(Answer answer) {
        this(List.of(answer));
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }


    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> createDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.createDeleteHistory());
        }

        return deleteHistories;
    }
}
