package nextstep.qna.domain;

import nextstep.qna.service.DeleteHistoryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
public class DeleteHistoryEventListener {

    private final DeleteHistoryService deleteHistoryService;

    public DeleteHistoryEventListener(DeleteHistoryService deleteHistoryService) {
        this.deleteHistoryService = deleteHistoryService;
    }

    @TransactionalEventListener(phase = AFTER_COMMIT, fallbackExecution = true)
    public void handleDeleteHistoryEvent(DeleteHistoryEvent event) {
        deleteHistoryService.saveAll(event.getQuestion().deletedHistories());
    }
}
