package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public boolean isNotOnlyWriter(NsUser loginUser) {
        return !answers.stream().allMatch(answer -> answer.isOwner(loginUser));
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> delete(NsUser questioner) {
        return answers.stream()
                .map(answer -> answer.delete(questioner))
                .collect(Collectors.toList());
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
