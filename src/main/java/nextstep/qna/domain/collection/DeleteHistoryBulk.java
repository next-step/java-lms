package nextstep.qna.domain.collection;

import nextstep.qna.domain.DeleteHistory;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryBulk {
    private final List<DeleteHistory> set = new ArrayList<>();

    public void add(DeleteHistory deleteHistory) {
        this.set.add(deleteHistory);
    }

    public void addAtFisrt(DeleteHistory deleteHistory) {
        this.set.add(0, deleteHistory);
    }

    public List<DeleteHistory> toList() {
        return List.copyOf(this.set);
    }
}