package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class DeleteQuestion {

    private final Question question;
    private final DeleteHistories deleteHistories;

    public DeleteQuestion(Question question, DeleteHistories deleteHistories) {
        this.question = question;
        this.deleteHistories = deleteHistories;
    }

    public void delete(NsUser loginUser, long questionId) {
        this.question.delete(loginUser);
        this.deleteHistories.add(question, questionId);
    }
}
