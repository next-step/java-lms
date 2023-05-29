package nextstep.qna.domain;

import nextstep.users.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    private Answers(List<Answer> answer) {
        this.answers = answer;
    }

    public static Answers of(List<Answer> answer) {
        return new Answers(answer);
    }

    public static Answers create() {
        return new Answers(List.of());
    }

    public boolean hasAnotherOwner(String requestUser) {
        return !this.answers
                .stream()
                .allMatch(answer -> answer.isOwner(requestUser));
    }

    public boolean hasAnswers() {
        return !answers.isEmpty();
    }

    public DeleteHistories removeAll() {

        List<DeleteHistory> deleteHistories = answers.stream()
                .map(Answer::remove)
                .collect(Collectors.toList());

        return DeleteHistories.of(deleteHistories);
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
