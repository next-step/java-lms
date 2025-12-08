package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(Answer answer) {
        this(List.of(answer));
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }


    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            answer.delete();
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        return deleteHistories;
    }

    public void validateDeletableBy(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateDeletableBy(loginUser);
        }
    }
}
