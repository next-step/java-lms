package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.deletehistory.DeleteHistory;
import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> values = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer, Question question) {
        answer.toQuestion(question);
        values.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser requestUser, LocalDateTime requestDatetime) throws CannotDeleteException {
        if (!isAllSameAnswererWith(requestUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : values) {
            deleteHistories.add(answer.delete(requestUser, requestDatetime));
        }
        return Collections.unmodifiableList(deleteHistories);
    }

    public List<Answer> values() {
        return Collections.unmodifiableList(this.values);
    }

    public boolean isAllSameAnswererWith(NsUser requestUser) {
        return this.values.stream()
                .allMatch(answer -> answer.isOwner(requestUser));
    }
}
