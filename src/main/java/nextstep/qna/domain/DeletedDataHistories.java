package nextstep.qna.domain;

import java.util.*;

public class DeletedDataHistories {

    private final Set<DeleteHistory> records;

    public DeletedDataHistories(Set<DeleteHistory> records) {
        this.records = records;
    }

    public void add(DeleteHistory deleted) {
        this.records.add(deleted);
    }

    public List<DeleteHistory> getRecords() {
        List<DeleteHistory> result = new ArrayList<>(records);
        result.sort(Comparator.comparing(DeleteHistory::getContentType));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeletedDataHistories)) return false;
        DeletedDataHistories that = (DeletedDataHistories) o;
        return Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }
}
