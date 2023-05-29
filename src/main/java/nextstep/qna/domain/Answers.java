package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> values;

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public Answers() {
        this(new ArrayList<>());
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : values) {
            deleteHistories.add(answer.deleteAnswer(loginUser));
        }
        return deleteHistories;
    }

    public List<Answer> getValues() {
        return values;
    }
}
