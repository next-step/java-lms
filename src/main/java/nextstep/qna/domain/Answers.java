package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class Answers {
    private List<Answer> answers;

    private Answers() {}

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void deleteBy(NsUser user, DeleteHistories deleteHistories) throws CannotDeleteException {
        for(Answer answer : answers){
            answer.deleteBy(user);
            deleteHistories.addAnswerDeleteHistory(answer);
        }
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
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
