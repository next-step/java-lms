package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.*;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer... answers) {
        this(new ArrayList<>(Arrays.asList(answers)));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void deleteAll(NsUser loginUser, DeleteHistories deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
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
