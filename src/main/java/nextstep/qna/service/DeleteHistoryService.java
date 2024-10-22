package nextstep.qna.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.qna.domain.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("deleteHistoryService")
public class DeleteHistoryService {
    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(List<DeleteHistory> deleteHistories) {
        deleteHistoryRepository.saveAll(deleteHistories);
    }
}
