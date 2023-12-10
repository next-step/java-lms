package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers() {

    }

    public void addAnswer(final Answer answer) {
        answers.add(answer);
    }

    public void validatePermission(NsUser loginUser) {
        answers.forEach(answer -> answer.validatePermission(loginUser));
    }

    public void deleteAnswers(List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        answers.forEach(answer -> deleteHistories.add(answer.deleteContent()));
    }
}
