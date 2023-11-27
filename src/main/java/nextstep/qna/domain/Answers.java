package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
    List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void verifyAllAnswerOwnerIsTargetUser(final NsUser user) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.verifyUserWithAnswerDeletionPermission(user);
        }
    }

    public void deleteAll(final DeleteHistories deleteHistories) {
        for (Answer answer : this.answers) {
            answer.delete(deleteHistories);
        }
    }
}
