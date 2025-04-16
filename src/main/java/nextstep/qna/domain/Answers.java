package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAllBy(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deleteBy(user);
        }
    }

    // TODO 잠시 테스트용
    public List<Answer> getAnswers() {
        return answers;
    }
}
