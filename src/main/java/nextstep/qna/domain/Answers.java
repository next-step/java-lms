package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.UnAuthenticationException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws UnAuthenticationException {

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            answer.delete(loginUser);
            deleteHistories.addAll(answer.delete(loginUser));
        }

        return deleteHistories;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

}
