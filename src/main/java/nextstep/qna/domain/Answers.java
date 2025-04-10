package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void deleteAll() {
        answers.forEach(Answer::delete);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
