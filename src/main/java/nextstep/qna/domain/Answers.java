package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers addAnswer(Answer newAnswer) {
        List<Answer> answers = new ArrayList<>(this.answers);
        answers.add(newAnswer);
        return new Answers(answers);
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "answers{" +
                "answers=" + answers +
                '}';
    }
}
