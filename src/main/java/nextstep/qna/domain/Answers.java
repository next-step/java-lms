package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void deleteAll(NsUser loginUser) throws CannotDeleteException {
        for (Answer it : answers) {
            it.delete(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }


}
