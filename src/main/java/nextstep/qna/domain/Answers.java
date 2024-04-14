package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(Answer... answers) {
        this();
        add(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void add(Answer... answers) {
        for (Answer answer : answers) {
            add(answer);
        }
    }

    public boolean isOwner(NsUser loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public void allDeleted() {
        answers.forEach(answer -> answer.deleted());
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public DeleteHistories delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        allDeleted();

        return new DeleteHistories(this);
    }
}
