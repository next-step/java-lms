package nextstep.qna.event;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.service.DeleteHistoryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class QuestionDeleteEventHandler {
    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @EventListener
    public void handler(QuestionDeleteEvent event) throws CannotDeleteException {
        deleteHistoryService.saveAll(event.deleteHistory());
    }
}
