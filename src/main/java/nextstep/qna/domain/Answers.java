package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void validateOnwer(NsUser user) throws CannotDeleteException {
        if (answers == null || answers.isEmpty()) {
            return;
        }

        if (answers.stream().noneMatch(answer -> answer.isOwner(user))) {
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
        return Objects.hashCode(answers);
    }

    public List<Answer> deleteAll() {
        List<Answer> result = new ArrayList<>();
        for (Answer answer : answers) {
            Answer deletedAnswer = answer.setDeleted(true);
            result.add(deletedAnswer);
        }
        return result;
    }
}
