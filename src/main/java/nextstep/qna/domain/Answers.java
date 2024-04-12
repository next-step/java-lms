package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void deleteAnswers(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.delete(loginUser);
        }
    }

    public void toDeleteHistories(List<DeleteHistory> deleteHistories) {
        for (Answer answer : this.answers) {
            deleteHistories.add(answer.toDeleteHistory());
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }
}
