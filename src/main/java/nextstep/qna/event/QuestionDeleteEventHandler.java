package nextstep.qna.event;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.service.DeleteHistoryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;

@Component
public class QuestionDeleteEventHandler {
    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handler(QuestionDeleteEvent event) throws CannotDeleteException {
        deleteHistoryService.saveAll(event.deleteHistory());
    }
}
