package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class AnswerUser {

    private List<Answer> users;

    public AnswerUser(List<Answer> users) {
        this.users = users;
    }

    public void add(Answer answer) {
        users.add(answer);
    }

    public void delete(List<DeleteHistory> deleteHistories, NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : users) {
            deleteHistories.add(answer.delete(loginUser));
        }
    }

    public List<Answer> getUsers() {
        return users;
    }

}
