package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> values;

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        try {
            for (Answer answer : values) {
                answer.delete(loginUser);
                deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
            }
            return deleteHistories;
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<Answer> get() {
        return values;
    }

    public void add(Answer answer) {
        values.add(answer);
    }
}
