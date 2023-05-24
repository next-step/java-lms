package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(Question question) {
        this.answers = question.getAnswers();
    }

    public void checkCanDelete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkCanDelete(loginUser);
        }
    }

    public List<DeleteHistory> makeDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.makeDeleteHistory());
        }
        return deleteHistories;
    }
}
