package nextstep.qna.domain;

import nextstep.qna.NotFoundException;

public class AnswerKey {
    private Long id;
    private Question question;

    public AnswerKey(Long id, Question question) {
        this.id = id;

        if (question == null) {
            throw new NotFoundException();
        }

        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }
}
