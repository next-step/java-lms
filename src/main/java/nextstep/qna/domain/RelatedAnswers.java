package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class RelatedAnswers {

    private final List<Answer> answers = new ArrayList<>();

    public RelatedAnswers() {
    }

    public RelatedAnswers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            answer.delete(loginUser);
            deleteHistories.add(answer.toDeleteHistory());
        }
        return deleteHistories;
    }
}
