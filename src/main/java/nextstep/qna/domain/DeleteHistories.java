package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nextstep.users.domain.NsUser;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.isNull(obj) || getClass() != obj.getClass()) {
            return false;
        }
        DeleteHistories that = (DeleteHistories) obj;
        return isContainsAllDeleteHistory(that);
    }

    private boolean isContainsAllDeleteHistory(DeleteHistories comparingHistories) {

        return deleteHistories.stream()
                              .allMatch(comparingHistories::contains);
    }

    public void addHistory(ContentType contentType, long questionId, NsUser writer) {
        deleteHistories.add(new DeleteHistory(contentType, questionId, writer, LocalDateTime.now()));
    }

    public boolean contains(DeleteHistory deleteHistory) {
        return deleteHistories.contains(deleteHistory);
    }
}
