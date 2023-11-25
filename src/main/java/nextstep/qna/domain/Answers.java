package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Answers {

    private final List<Answer> answers;

    public static Answers init() {
        return new Answers(Collections.emptyList());
    }

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers added(Answer answer) {
        List<Answer> answers = new ArrayList<>(this.answers);
        answers.add(answer);
        return new Answers(answers);
    }

    public boolean hasAnswerExcept(NsUser writer) {
        return this.answers.stream()
            .filter(answer -> !answer.isOwner(writer))
            .findAny()
            .isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
