package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }


    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
