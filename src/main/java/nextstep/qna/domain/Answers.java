package nextstep.qna.domain;

import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public void validateAnswersOwnership(final NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            validateOwnership(answer, loginUser);
        }
    }

    private void validateOwnership(final Answer answer, final NsUser loginUser) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
