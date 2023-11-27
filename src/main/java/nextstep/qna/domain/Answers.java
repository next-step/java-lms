package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public Answers(Answer... answers) {
        Arrays.stream(answers)
                .forEach(this::add);
    }

    public Answers add(Answer answer) {
        answers.add(answer);
        return new Answers(answers);
    }

    public int size() {
        return answers.size();
    }

    public List<DeleteHistory> delete(NsUser writer) {
        return answers.stream()
                .map(answer -> answer.delete(writer))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Answers answers1 = (Answers) obj;
        return answers.equals(answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
