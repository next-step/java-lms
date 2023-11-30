package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer, Question question) {
        answer.toQuestion(question);
        answers.add(answer);
    }

    public List<DeleteHistory> deleteBy(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            deleteHistories.add(answer.deleteBy(user));
        }
        return deleteHistories;
    }
}
