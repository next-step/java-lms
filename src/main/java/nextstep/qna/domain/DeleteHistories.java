package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public void addAll(DeleteHistories source) {
        this.deleteHistories.addAll(source.getDeleteHistories());
    }
}
