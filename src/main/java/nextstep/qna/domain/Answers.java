package nextstep.qna.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers(Answer...answers) {
        this(Arrays.stream(answers).collect(Collectors.toList()));
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete() {
        answers.forEach(Answer::delete);
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answers)) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
