package nextstep.qna.domain;

import nextstep.qna.service.QnAService;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.Objects;

public class DeleteHistoryEvent extends ApplicationEvent {

    private final DeleteHistories deleteHistories;

    public DeleteHistoryEvent(QnAService qnAService, DeleteHistories deleteHistories) {
        super(qnAService);
        this.deleteHistories = deleteHistories;
    }

    public List<DeleteHistory> deletedHistories() {
        return deleteHistories.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistoryEvent that = (DeleteHistoryEvent) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(deleteHistories);
    }
}
