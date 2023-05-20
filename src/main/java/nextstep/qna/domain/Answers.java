package nextstep.qna.domain;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    private Answers(List<Answer> answer) {
        this.answers = answer;
    }

    public static Answers of(List<Answer> answer) {
        return new Answers(answer);
    }

}
