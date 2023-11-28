package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        if(answers == null) {
            this.answers = new ArrayList<>();
            return;
        }
        this.answers = answers;
    }

    public void checkDelete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkCanDelete(loginUser);
        }
    }

    public void setDeleted() {
        answers.forEach(Answer::setDeleted);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> makeDeleteHistory(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.makeDeleteHistory(loginUser));
        }
        return deleteHistories;
    }
}
