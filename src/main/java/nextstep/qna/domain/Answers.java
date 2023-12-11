package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> values;

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public List<DeleteHistory> deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : values) {
            validateDeleteAnswerPermission(answer, loginUser);
        }
        return deleteHistory();
    }

    private void validateDeleteAnswerPermission(Answer answer, NsUser loginUser) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private List<DeleteHistory> deleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : values) {
            answer.delete();
            deleteHistories.add(answer.createDeleteHistory());
        }
        return deleteHistories;
    }
}
