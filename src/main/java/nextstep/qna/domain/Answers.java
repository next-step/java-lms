package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Question question, Answer answer) {
        answer.toQuestion(question);
        answers.add(answer);
    }

    public DeleteHistories deleteAnswers(NsUser loginUser) {
        DeleteHistories deleteHistories = new DeleteHistories();

        for (Answer answer : answers) {
            deleteHistories.addDeleteHistory(answer.delete(loginUser));
        }
        return deleteHistories;
    }
}
