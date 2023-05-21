package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private static final String DELETE_ERROR_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateAnswerUser(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }

        return  deleteHistories;
    }

    private void validateAnswerUser(NsUser loginUser) throws CannotDeleteException {
        if(containsNotOwnerAnswer(loginUser)) {
            throw new CannotDeleteException(DELETE_ERROR_MESSAGE);
        }
    }

    private boolean containsNotOwnerAnswer(NsUser loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
