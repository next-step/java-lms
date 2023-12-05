package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DeleteHistories implements Iterable<DeleteHistory> {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this(new ArrayList<>());
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistories inputDeleteHistories) {
        for(DeleteHistory deleteHistory : inputDeleteHistories) {
            this.add(deleteHistory);
        };
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    @Override
    public Iterator<DeleteHistory> iterator() {
        return this.deleteHistories.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
