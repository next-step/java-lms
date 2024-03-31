package nextstep.qna.service;

import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.qna.domain.DeleteHistorys;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("deleteHistoryService")
public class DeleteHistoryService {
    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(DeleteHistorys deleteHistories) {
        deleteHistoryRepository.saveAll(deleteHistories);
    }
}
