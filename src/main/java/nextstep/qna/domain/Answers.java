package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> values;

    private Answers(List<Answer> values) {
        this.values = values;
    }

    public static Answers from(List<Answer> values) {
        return new Answers(values);
    }

    public void addAnswer(Answer answer) {
        values.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser nsUser) {
        try {
            return values.stream()
                    .map(answer -> answer.delete(nsUser))
                    .collect(Collectors.toList());
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.", e);
        }
    }
}
