package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public DeletedDataHistories deleteAll(NsUser loginUser) throws CannotDeleteException {
        Set<DeleteHistory> deleteHistories = new HashSet<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return new DeletedDataHistories(deleteHistories);
    }
}
