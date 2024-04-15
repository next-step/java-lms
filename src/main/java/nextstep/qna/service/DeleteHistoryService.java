package nextstep.qna.service;

import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.qna.event.DeleteHistorySaveEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("deleteHistoryService")
public class DeleteHistoryService {
    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(DeleteHistorySaveEvent deleteHistorySaveEvent) {
        deleteHistoryRepository.saveAll(deleteHistorySaveEvent.getDeleteHistories());
    }
}
