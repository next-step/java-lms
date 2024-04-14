package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RelatedAnswers {

    private final List<Answer> answers = new ArrayList<>();

    public RelatedAnswers() {
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void deleteAll(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> toDeleteHistories() {
        return answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
    }
}
