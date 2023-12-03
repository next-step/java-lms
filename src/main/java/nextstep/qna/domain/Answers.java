package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<Answer> allAnswers() {
        return this.answers;
    }

    public void allDeleted(NsUser loginUser) throws CannotDeleteException {
        for(Answer answer : this.answers) {
            answer.deleted(loginUser);
        }
    }
}
