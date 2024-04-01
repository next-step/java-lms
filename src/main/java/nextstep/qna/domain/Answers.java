package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
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

    public Answers delete(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(user);
        }
        return this;
    }

    public void addTo(DeleteHistories histories) {
        answers.forEach(answer -> answer.addTo(histories));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
