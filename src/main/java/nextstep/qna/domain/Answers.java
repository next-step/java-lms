package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answerList;

    public Answers() {
        this.answerList = new ArrayList<>();
    }

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answerList) {
            deleteHistories.add(answer.delete(loginUser));
        }

        return deleteHistories;
    }

    public void add(Answer answer) {
        answerList.add(answer);
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }
}
