package nextstep.qna.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeleteHistories implements Serializable {

    private final List<DeleteHistory> histories;

    public DeleteHistories(List<DeleteHistory> histories) {
        this.histories = histories;
    }

    public static DeleteHistories of(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.ofQuestion(question));
        deleteHistories.addAll(question.getAnswers().stream()
                .map(DeleteHistory::ofAnswer)
                .collect(Collectors.toList()));
        return new DeleteHistories(deleteHistories);
    }

    public List<DeleteHistory> value() {
        return Collections.unmodifiableList(histories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(histories, that.histories);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(histories);
    }
}
