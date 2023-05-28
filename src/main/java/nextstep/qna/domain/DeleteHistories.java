package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;


    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = new ArrayList<>(deleteHistories);
    }

    public int size() {
        return deleteHistories.size();
    }

    public List<DeleteHistory> immutableGet() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
