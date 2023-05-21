package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers implements Iterable<Answer> {

    private List<Answer> values;

    public Answers() {
        this.values = new ArrayList<>();
    }

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        for (Answer answer : values) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }

        return values.stream()
            .map(Answer::delete)
            .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers = (Answers) o;
        return Objects.equals(values, answers.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public Iterator<Answer> iterator() {
        return values.iterator();
    }
}
