package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> values = new ArrayList<>();

    public void add(Answer answer) {
        values.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) {
        for (Answer answer : values) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : values) {
            deleteHistories.add(answer.deleteAnswer());
        }
        return deleteHistories;
    }

    public List<Answer> getValues() {
        return values;
    }
}
