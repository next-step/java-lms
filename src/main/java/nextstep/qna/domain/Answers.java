package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    private final DeleteHistoryList deleteHistoryList = new DeleteHistoryList();

    public Answers() {

    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public DeleteHistoryList getDeleteHistoryList() {
        return deleteHistoryList;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            deleteHistoryList.add(answer.delete(loginUser));
        }
    }
}
