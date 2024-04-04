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

    public List<DeleteHistory> deleteAnswersBy(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : this.answers) {
            histories.add(answer.deleteAnswerBy(user));
        }
        return histories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

}
