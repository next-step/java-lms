package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void addAll(DeleteHistories histories) {
        this.deleteHistories.addAll(histories.deleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return this.deleteHistories;
    }
}
