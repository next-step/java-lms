package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void deleteAll() {
        for (Answer answer : answers) {
            answer.delete();
        }
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }
}
