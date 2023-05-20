package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void isDeletable(NsUser loginUser) throws CannotDeleteException {
        if (hasOtherAnswerUser(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean hasOtherAnswerUser(NsUser loginUser) {
        return answers.stream()
                .anyMatch(answer -> answer.isOwner(loginUser));
    }
}
