package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.UnAuthenticationException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws UnAuthenticationException {
        validateAnswerWriter(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.addAll(answer.delete(loginUser));
        }

        return deleteHistories;
    }

    private void validateAnswerWriter(NsUser loginUser) throws UnAuthenticationException {
        if (containAnotherOwner(loginUser)) {
            throw new UnAuthenticationException("삭제할 권한이 없습니다.");
        }
    }

    private boolean containAnotherOwner(NsUser loginUser) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
