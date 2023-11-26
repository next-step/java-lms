package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
    private List<Answer> answers;

    private Answers() {}

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void deleteBy(NsUser user) throws CannotDeleteException {
        for(Answer answer : answers){
            answer.deleteBy(user);
        }
    }
}
