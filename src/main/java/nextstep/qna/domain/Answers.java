package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void validateOwner(NsUser user) throws CannotDeleteException {
        if (isNotOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isNotOwner(NsUser user) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(user));
    }

    public List<DeleteHistory> deleteAnswers() {
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }
}
