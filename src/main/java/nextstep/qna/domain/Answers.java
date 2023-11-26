package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> list;

    public Answers(List<Answer> answerList) {
        this.list = answerList;
    }

    public List<DeleteHistory> delete(NsUser nsUser) {
        return list.stream()
                .map(answer -> answer.delete(nsUser))
                .collect(Collectors.toList());
    }

    public Answers add(Answer answer) {
        list.add(answer);
        return new Answers(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers = (Answers) o;
        return Objects.equals(list, answers.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

}
