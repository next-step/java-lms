package nextstep.qna.domain.deletehistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> histories = new ArrayList<>();

    public DeleteHistories(DeleteHistory history) {
        this.histories.add(history);
    }

    public void addAll(List<DeleteHistory> histories) {
        if (histories == null || histories.isEmpty()) {
            return;
        }
        this.histories.addAll(histories);
    }

    public List<DeleteHistory> values() {
        return Collections.unmodifiableList(this.histories);
    }
}
