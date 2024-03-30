package nextstep.qna.service;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.qna.domain.deleteHistory.DeleteHistories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
 * 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
 * 답변이 없는 경우 삭제가 가능하다.
 * 질문자와답변글의모든답변자같은경우삭제가가능하다.
 * 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
 * 한다.
 * 질문자와답변자가다른경우답변을삭제할수없다.
 * 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
 */
@Service("deleteHistoryService")
public class DeleteHistoryService {
    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(List<DeleteHistory> deleteHistories) {
        deleteHistoryRepository.saveAll(deleteHistories);
    }
}
