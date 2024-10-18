package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    List<Answer> answerList;

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void add(Answer answer) {
        answerList.add(answer);
    }

    public List<DeleteHistory> delete(NsUser nsUser) throws CannotDeleteException {

       List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answerList) {
            deleteHistories.add(answer.delete(nsUser));
        }
        return deleteHistories;
    }
}
