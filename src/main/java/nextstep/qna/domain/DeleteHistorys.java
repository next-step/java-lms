package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistorys {
    private final List<DeleteHistory> deleteHistoryList = new ArrayList<>();
    public void add(DeleteHistory deleteHistory) {
        deleteHistoryList.add(deleteHistory);
    }

    public List<DeleteHistory> getDeleteHistoryList() {
        return Collections.unmodifiableList(deleteHistoryList);
    }

    public void addAll(List<DeleteHistory> histories) {
        this.deleteHistoryList.addAll(histories);
    }
}
