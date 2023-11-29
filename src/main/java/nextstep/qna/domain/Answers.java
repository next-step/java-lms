package nextstep.qna.domain;

import java.util.List;

public class Answers {
    private final List<Answer> Answers;

    public Answers(List<Answer> answers) {
        this.Answers = answers;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "Answers=" + Answers +
                '}';
    }
}
