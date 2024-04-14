package nextstep.common.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryRepository;
import nextstep.common.dto.DeleteHistoryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("deleteHistoryService")
public class DeleteHistoryService {
    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(List<DeleteHistory> deleteHistories) {
        List<DeleteHistoryDto> deleteHistoryDto = deleteHistories.stream()
                .map(DeleteHistory::toVO)
                .collect(Collectors.toList());

        deleteHistoryRepository.saveAll(deleteHistoryDto);
    }
}
