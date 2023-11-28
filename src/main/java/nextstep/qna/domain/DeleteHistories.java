package nextstep.qna.domain;

import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private final List<DeleteHistory> histories;

    public DeleteHistories(List<DeleteHistory> histories) {
        this.histories = histories;
    }

    public void add(DeleteHistory deleteHistory) {
        this.histories.add(deleteHistory);
    }

    public List<DeleteHistory> getValues() {
        return List.copyOf(this.histories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories histories1 = (DeleteHistories) o;
        return Objects.equals(histories, histories1.histories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(histories);
    }
}
