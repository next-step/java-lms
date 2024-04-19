package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers implements Iterable<Answer> {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
