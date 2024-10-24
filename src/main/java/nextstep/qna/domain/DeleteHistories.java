package nextstep.qna.domain;

import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> histories;

    public DeleteHistories(List<DeleteHistory> histories) {
        this.histories = histories;
    }

    public void add(DeleteHistory deleteHistory) {
        histories.add(deleteHistory);
    }

    public void addAll(List<DeleteHistory> deleteHistories) {
        histories.addAll(deleteHistories);
    }

    public List<DeleteHistory> getHistories() {
        return Collections.unmodifiableList(histories);
    }
}
