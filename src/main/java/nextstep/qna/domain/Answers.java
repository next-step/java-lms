package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void validateDeleteQuestionAnswers(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateDeleteQuestionAnswer(loginUser);
        }
    }

    public void deleteQuestionAllAnswers(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            deleteHistories.addDeleteHistory(answer.deleteAnswer());
        }
    }
}
