package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isEmpty() {
        return answers.isEmpty();
    }


    public List<DeleteHistory> delete() {
        List<DeleteHistory> result = new ArrayList<>();
        for (Answer answer : answers) {
            result.add(answer.delete());
        }
        return result;
    }
}
