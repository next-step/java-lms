package nextstep.qna.domain;

import java.util.Iterator;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers implements Iterable<Answer> {

    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void deleteValidate(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deleteValidation(loginUser);
        }
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
