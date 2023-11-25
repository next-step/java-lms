package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class DeleteAnswer {

    private final Question question;
    private final Answers answers;
    private final DeleteHistories deleteHistories;

    public DeleteAnswer(Question question, DeleteHistories deleteHistories) {
        this.answers = new Answers(question.getAnswers());
        this.question = question;
        this.deleteHistories = deleteHistories;
    }

    public void delete(NsUser loginUser) {
        for (Answer answer : this.answers.getAnswers()) {
            answer.delete(loginUser);
            deleteHistories.add(answer);
        }
    }
}
