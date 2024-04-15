package nextstep.qna.event;

import nextstep.qna.domain.DeleteHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deleteHistorySavePublisher")
public class DeleteHistorySavePublisher {
    private final ApplicationEventPublisher publisher;

    @Autowired
    public DeleteHistorySavePublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(List<DeleteHistory> deleteHistories) {
        publisher.publishEvent(new DeleteHistorySaveEvent(this, deleteHistories));
    }
}
