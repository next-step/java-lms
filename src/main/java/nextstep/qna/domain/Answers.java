package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.*;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public Answers(Answer... answers) {
        this(Arrays.asList(answers));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public DeleteHistories deleteAll(NsUser loginUser) throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.delete(loginUser);
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
