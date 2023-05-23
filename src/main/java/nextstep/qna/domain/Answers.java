package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (containsAnswerUserDifferentWith(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return this.answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private boolean containsAnswerUserDifferentWith(NsUser loginUser) {
        return this.answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }
}
