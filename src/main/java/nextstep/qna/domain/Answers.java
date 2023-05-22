package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(Answer... answers) {
        this.answers = Arrays.stream(answers)
                .collect(Collectors.toList());
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateDelete(loginUser);
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDelete(NsUser loginUser) throws CannotDeleteException {
        if (hasNotOwnerAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.ㅣ");
        }
    }

    private boolean hasNotOwnerAnswer(NsUser loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public void add(Answer answer) {
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
