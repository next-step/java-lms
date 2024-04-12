package nextstep.qna.domain;

import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> value;

    public DeleteHistories(List<DeleteHistory> deleteHistoryList) {
        this.value = deleteHistoryList;
    }

    public List<DeleteHistory> getValue() {
        return value;
    }
}
