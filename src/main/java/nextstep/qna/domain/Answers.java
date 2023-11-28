package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {

    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void deleteAll(NsUser loginUser) throws CannotDeleteException {
        validateOtherWriter(loginUser);

        answers.forEach(Answer::delete);
    }

    private void validateOtherWriter(NsUser loginUser) throws CannotDeleteException {
        if (isOtherUser(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isOtherUser(NsUser loginUser) {
        return answers.stream()
            .anyMatch(answer -> !answer.isSameWriter(loginUser));
    }

    public boolean isDeleted(int idx) {
        return answers.get(idx).isDeleted();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
