package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = List.copyOf(answers);
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void delete(NsUser longinUser) {
        this.answers.forEach(answer -> answer.delete(longinUser));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAll() {
        return this.answers;
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
