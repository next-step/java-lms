package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public static DeleteHistories create() {
        return new DeleteHistories(new ArrayList<>());
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void concat(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.getDeleteHistories());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(this.deleteHistories);
    }
}
