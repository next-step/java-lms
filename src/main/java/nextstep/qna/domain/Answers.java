package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        if (Objects.isNull(answer)) {
            throw new NullPointerException("null은 답변리스트(Answers)에 추가될 수 없습니다.");
        }
        this.answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void deleteAnswers() throws CannotDeleteException {
        if (answers.isEmpty()) {
            return;
        }

        for (Answer answer : answers) {
            answer.delete();
        }
    }
}
