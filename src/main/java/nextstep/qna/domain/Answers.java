package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            checkOwner(user);
            answer.delete();
            deleteHistories.add((new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));
        }

        return deleteHistories;
    }

    private void checkOwner(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            validateAnswerWithUser(user, answer);
        }
    }

    private void validateAnswerWithUser(NsUser user, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
