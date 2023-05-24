package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public List<DeleteHistory> addDeleteHistory(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
        return deleteHistories;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
