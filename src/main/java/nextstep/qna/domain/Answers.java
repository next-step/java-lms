package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        answers = new ArrayList<>();
    }

    public List<DeleteHistory> delete(final NsUser loginUser) throws CannotDeleteException {
        validateAnswersOwnership(loginUser);
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }

    private void validateAnswersOwnership(final NsUser loginUser) throws CannotDeleteException {
        for (final Answer answer : answers) {
            answer.validateOwnership(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
