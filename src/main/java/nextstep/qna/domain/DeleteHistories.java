package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void add(Question question) {
        this.deleteHistories.add(new DeleteHistory(
                ContentType.QUESTION, question.getId(), question.getWriter()));
    }

    public void add(Answer answer) {
        this.deleteHistories.add(new DeleteHistory(
                ContentType.QUESTION, answer.getId(), answer.getWriter()));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}