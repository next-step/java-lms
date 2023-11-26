package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (!answers.isEmpty()) {
            deleteAnswers(loginUser);
        }
    }

    private void deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer: answers) {
            answer.delete(loginUser);
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isAllDeleted() {
        return answers.stream()
            .allMatch(Answer::isDeleted);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return answers.stream()
            .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()))
            .collect(Collectors.toList());
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
