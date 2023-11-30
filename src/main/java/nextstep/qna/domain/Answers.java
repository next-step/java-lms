package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void deleteAll(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
        validateOtherWriter(loginUser);

        answers.forEach(answer -> answer.delete(now));
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

    public List<DeleteHistory> deleteHistories() {
        return this.answers.stream()
            .map(Answer::createDeleteHistory)
            .collect(Collectors.toList());
    }
}
