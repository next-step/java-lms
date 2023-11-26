package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
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

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void deleteAll(final DeleteHistories deleteHistories) {
        for (Answer answer : this.answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }
}
