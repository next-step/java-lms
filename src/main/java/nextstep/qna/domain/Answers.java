package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) {
        return this.answers.stream()
                .map(answer -> {
                    DeleteHistory deleteHistory = answer.delete(loginUser);
                    return deleteHistory;
                }).collect(Collectors.toList());
    }

    public List<Answer> value() {
        return Collections.unmodifiableList(this.answers);
    }
}
