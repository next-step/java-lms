package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> values;

    public Answers() {
        this.values = new ArrayList<>();
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public void validateOwnerships(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : values) {
            answer.validateOwnership(loginUser);
        }
    }

    public List<DeleteHistory> deleteAnswers() {
        return values.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }
}
