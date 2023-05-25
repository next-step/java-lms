package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Answers implements Iterable<Answer> {

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

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
