package nextstep.qna.domain.deleteHistory;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.answer.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
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

    public List<DeleteHistory> getList() {
        return this.deleteHistories;
    }
}
