package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryList {
    private final List<DeleteHistory> deleteHistoryList = new ArrayList<>();

    public DeleteHistoryList() {

    }

    public List<DeleteHistory> getDeleteHistoryList() {
        return deleteHistoryList;
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistoryList.add(deleteHistory);
    }

    public void addAll(DeleteHistoryList deleteHistoryList) {
        this.deleteHistoryList.addAll(deleteHistoryList.getDeleteHistoryList());
    }
}
