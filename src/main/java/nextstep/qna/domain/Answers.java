package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
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

    private void allDeleted(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        allDeleted(loginUser);

        return answers.stream()
                .map(DeleteHistory::new)
                .collect(Collectors.toList());

    }
}
