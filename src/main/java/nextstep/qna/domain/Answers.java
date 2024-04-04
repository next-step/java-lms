package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void deleteBy(NsUser user) throws CannotDeleteException {
        if (!isDeletableBy(user)) {
            throw new CannotDeleteException("현재 로그인 계정과 다른 답변 작성자가 있습니다.");
        }
    }

    private boolean isDeletableBy(NsUser user) {
        return this.answers.stream().allMatch(answer -> answer.isDeletableBy(user));
    }
}
