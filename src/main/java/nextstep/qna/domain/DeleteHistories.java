package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    List<DeleteHistory> histories;

    public DeleteHistories() {
        this.histories = new ArrayList<>();
    }

    public void add(DeleteHistory deleteHistory) {
        this.histories.add(deleteHistory);
    }

    public int size() {
        return this.histories.size();
    }

    public List<DeleteHistory> getHistories() {
        return this.histories;
    }

    public void add(DeleteHistories deleteHistories) {
        this.histories.addAll(deleteHistories.getHistories());
    }
}
