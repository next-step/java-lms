package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class AnswerList {

    private final List<Answer> answers = new ArrayList<>();

    public void validateUser(final NsUser loginUser) throws CannotDeleteException {
        for (final Answer answer : answers) {
            answer.isOwner(loginUser);
        }
    }

    public void add(final Answer answer) {
        System.out.println("sdfsdf");
        answers.add(answer);
    }

    public void delete(final LocalDateTime now, final List<DeleteHistory> deleteHistories) {
        for (final Answer answer : answers) {
            DeleteHistory deleteHistory = answer.delete(now);
            deleteHistories.add(deleteHistory);
        }
    }
}
