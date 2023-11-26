package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void add(DeleteHistories DeleteHistories) {
        deleteHistories.addAll(DeleteHistories.deleteHistories);
    }

    public List<DeleteHistory> deleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
