package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers(Answer...answers) {
        this(Arrays.stream(answers).collect(Collectors.toList()));
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser, LocalDateTime createdDate) throws CannotDeleteException {
        validateAnswerUser(loginUser);
        return answers.stream()
                .map(answer -> answer.delete(createdDate))
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateAnswerUser(NsUser loginUser) throws CannotDeleteException {
        if(containsNotOwnedAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }

    private boolean containsNotOwnedAnswer(NsUser loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answers)) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
