package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void addDeleteHistories(List<DeleteHistory> deleteHistories) {
        answers.forEach(answer -> deleteHistories.add(answer.createDeleteHistory()));
    }

    public void checkOtherAnswers(NsUser loginUser) throws CannotDeleteException{
        for (Answer answer : answers) {
            answer.checkOtherAnswer(loginUser);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Answers))
            return false;
        Answers newAnswers = (Answers) object;
        return Objects.equals(answers, newAnswers.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
