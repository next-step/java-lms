package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    private void validateDelete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.validateDelete(loginUser);
        }
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateDelete(loginUser);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            DeleteHistory deleteAnswerHistory = answer.delete(deleteHistories);
            deleteHistories.add(deleteAnswerHistory);
        }
        return deleteHistories;
    }

    public int size() {
        return answers.size();
    }

    public boolean contains(Answer answer) {
        return answers.contains(answer);
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
