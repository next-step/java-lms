package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> values = new ArrayList<>();

    public Answers() {
        this.values = new ArrayList<>();
    }

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public List<Answer> getValues() {
        return Collections.unmodifiableList(values);
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : values) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }
}
