package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers() {

    }

    public Answers(final List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void addAnswer(final Answer answer) {
        answers.add(answer);
    }

    public void deleteAnswers(final NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

}
