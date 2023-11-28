package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {

    private final List<Answer> values;

    private Answers(List<Answer> values) {
        this.values = values;
    }

    public static Answers from(List<Answer> values) {
        return new Answers(values);
    }

    public void deleteAll(NsUser nsUser) {
        try {
            values.forEach(answer -> answer.delete(nsUser));
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException("다른 사용자의 답변이 존재합니다.", e);
        }
    }
}
