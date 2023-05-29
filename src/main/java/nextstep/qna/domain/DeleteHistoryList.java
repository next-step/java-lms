package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryList {
    List<DeleteHistory> deleteHistoryList = new ArrayList<>();

    public DeleteHistoryList() {

    }

    public List<DeleteHistory> getDeleteHistoryList() {
        return deleteHistoryList;
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistoryList.add(deleteHistory);
    }
}
