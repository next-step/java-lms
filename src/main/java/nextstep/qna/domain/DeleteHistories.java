package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public void addHistory(DeleteHistory deleteHistories) {
        this.deleteHistories.add(deleteHistories);
    }

    public void addALLHistory(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.getDeleteHistories());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
