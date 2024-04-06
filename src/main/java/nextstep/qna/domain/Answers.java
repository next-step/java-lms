package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }


    public List<DeleteHistory> delete() throws CannotDeleteException {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory history = answer.delete();
            histories.add(history);
        }
        return histories;
    }
}
