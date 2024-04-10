package nextstep.qna.domain;

import java.util.*;

public class DeletedHistories {

    private final Set<DeleteHistory> deleteHistories;

    public DeletedHistories(Set<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleted) {
        this.deleteHistories.add(deleted);
    }

    public List<DeleteHistory> getDeleteHistories() {
        List<DeleteHistory> result = new ArrayList<>(deleteHistories);
        result.sort(Comparator.comparing(DeleteHistory::getContentType));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeletedHistories)) return false;
        DeletedHistories that = (DeletedHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
