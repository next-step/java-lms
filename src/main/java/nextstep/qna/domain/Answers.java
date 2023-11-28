package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            validateAnswerOwner(user, answer);
            deleteHistories.add(answer.delete(user));
        }

        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    private static void validateAnswerOwner(NsUser user, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
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

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }
}
