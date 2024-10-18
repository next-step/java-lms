package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }


    public List<DeleteHistory> deleteAnswers(NsUser user) throws CannotDeleteException {
        validateOwner(user);
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    void validateOwner(NsUser user) throws CannotDeleteException {
        if (isNotOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isNotOwner(NsUser user) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(user));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
