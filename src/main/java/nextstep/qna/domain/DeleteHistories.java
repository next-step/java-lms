package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    private DeleteHistories() {
    }

    public static DeleteHistories create() {
        return new DeleteHistories();
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void concat(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.getDeleteHistories());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return this.deleteHistories;
    }
}
