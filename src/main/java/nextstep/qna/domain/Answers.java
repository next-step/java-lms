package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers delete(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(user);
        }
        return this;
    }

    public List<DeleteHistory> convertToDeleteHistories() {
        return answers.stream()
                .map(Answer::convertToDeleteHistory)
                .collect(Collectors.toUnmodifiableList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
