package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.addAll(answer.delete(loginUser));
        }

        return deleteHistories;
    }
}
