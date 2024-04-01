package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<Answer> get() {
        return this.answers;
    }

    public void canDelete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.canDelete(loginUser);
        }
    }

    public List<DeleteHistory> toDeleteHistory() {
        return this.answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Answers answers1 = (Answers) other;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
