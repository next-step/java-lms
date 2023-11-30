package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers() {

    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateDeletePermission(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    private void validateDeletePermission(NsUser user) throws CannotDeleteException {
        if (!this.isOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isOwner(NsUser user) {
        return this.answers.stream()
                .filter(answer -> !answer.isOwner(user))
                .count() == 0;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
