package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> values = new ArrayList<>();

    public List<Answer> getValues() {
        return values;
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    List<DeleteHistory> deleteAll(NsUser loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : values) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }
}
