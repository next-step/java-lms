package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final Question question;
    private final List<Answer> answers;

    public Answers(Question question) {
        this.question = question;
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(question);
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
