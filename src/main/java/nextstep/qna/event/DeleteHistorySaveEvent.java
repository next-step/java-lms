package nextstep.qna.event;

import nextstep.qna.domain.DeleteHistory;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class DeleteHistorySaveEvent extends ApplicationEvent {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistorySaveEvent(Object source, List<DeleteHistory> deleteHistories) {
        super(source);
        this.deleteHistories = deleteHistories;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
