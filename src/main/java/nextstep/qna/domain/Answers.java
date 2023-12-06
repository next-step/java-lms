package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

}
