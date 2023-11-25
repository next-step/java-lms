package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer: answers) {
            answer.delete(loginUser);
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isEmpty() {
        return answers.isEmpty();
    }

    public boolean isAllDeleted() {
        return answers.stream()
            .allMatch(Answer::isDeleted);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
