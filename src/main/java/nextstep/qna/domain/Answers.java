package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.CannotConvertException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);

        for (Answer answer : this.answers) {
            answer.delete();
        }
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

    public DeleteHistoryTargets asDeleteHistoryTargets() {
        validateDeleteStatus();

        return new DeleteHistoryTargets(convertAnswersToDeleteHistories());
    }

    private void validateDeleteStatus() {
        if (hasNotDeletedStatus()) {
            throw new CannotConvertException("삭제되지 않은 답변이 있어서 DeleteHistoryTargets으로 변환할 수 없습니다.");
        }
    }

    private boolean hasNotDeletedStatus() {
        return !answers.stream()
                .allMatch(Answer::isDeleted);
    }

    private List<DeleteHistory> convertAnswersToDeleteHistories() {
        return answers.stream()
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
