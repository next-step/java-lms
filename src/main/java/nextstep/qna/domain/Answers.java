package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> value;

    public Answers(List<Answer> value) {
        this.value = value;
    }

    public List<Answer> getValue() {
        return this.value;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistoryList = new ArrayList<>();

        for (Answer answer : this.value) {
            deleteHistoryList.add(answer.delete(loginUser));
        }

        return deleteHistoryList;
    }

    public void add(Answer answer) {
        this.value.add(answer);
    }
}
