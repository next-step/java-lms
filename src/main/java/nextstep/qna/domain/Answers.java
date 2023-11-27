package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public DeleteHistories deleteAll(final NsUser user) throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();

        for (Answer answer : this.answers) {
            deleteHistories.add(answer.delete(user));
        }

        return deleteHistories;
    }
}
