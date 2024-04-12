package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.deletehistory.DeleteHistory;
import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> values = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer, Question question) {
        answer.toQuestion(question);
        values.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser requestUser, LocalDateTime requestDatetime) {
        if (!isAllSameAnswererWith(requestUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        return values.stream()
                .map(answer -> answer.delete(requestUser, requestDatetime))
                .collect(Collectors.toList());
    }

    public List<Answer> values() {
        return Collections.unmodifiableList(this.values);
    }

    public boolean isAllSameAnswererWith(NsUser requestUser) {
        return this.values.stream()
                .allMatch(answer -> answer.isOwner(requestUser));
    }
}
