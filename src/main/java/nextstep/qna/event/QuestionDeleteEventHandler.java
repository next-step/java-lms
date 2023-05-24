package nextstep.qna.event;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.service.DeleteHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QuestionDeleteEventHandler {

    private final DeleteHistoryService deleteHistoryService;

    @Autowired
    public QuestionDeleteEventHandler(DeleteHistoryService deleteHistoryService) {
        this.deleteHistoryService = deleteHistoryService;
    }

    @EventListener
    public void handler(QuestionDeleteEvent event) throws CannotDeleteException {
        deleteHistoryService.saveAll(event.deleteHistory());
    }
}
