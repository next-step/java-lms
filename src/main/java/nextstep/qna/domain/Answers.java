package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.CannotConvertException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public DeleteHistoryTargets delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : this.answers) {
            deleteHistories.add(answer.delete());
        }
        return new DeleteHistoryTargets(deleteHistories);
    }

    private void validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (hasNotOwnerAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean hasNotOwnerAnswer(NsUser loginUser) {
        return !answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
