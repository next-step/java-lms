package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
    private final List<Answer> values;

    private Answers(List<Answer> values) {
        this.values = values;
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(new ArrayList<>(answers));
    }

    public static Answers empty() {
        return new Answers(new ArrayList<>());
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public void validateAllOwnedBy(NsUser user) throws CannotDeleteException {
        boolean hasOtherOwner =
            values.stream().anyMatch(a -> !a.isOwner(user));

        if (hasOtherOwner) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void deleteAll() {
        for (Answer answer : values) {
            answer.delete();
        }
    }

    public List<DeleteHistory> createDeleteHistories(LocalDateTime now) {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : values) {
            histories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), now));
        }
        return histories;
    }
}
