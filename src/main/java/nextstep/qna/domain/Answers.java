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

    public List<DeleteHistory> deleteAnswers(NsUser loginUser) {
        return values.stream()
                .map(value -> {
                    try {
                        return value.delete(loginUser);
                    } catch (CannotDeleteException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
