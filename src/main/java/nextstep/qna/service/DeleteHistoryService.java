package nextstep.qna.service;

import nextstep.qna.domain.DeleteHistories;
import nextstep.qna.domain.DeleteHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("deleteHistoryService")
public class DeleteHistoryService {
    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(DeleteHistories deleteHistories) {
        deleteHistoryRepository.saveAll2(deleteHistories);
    }
}
