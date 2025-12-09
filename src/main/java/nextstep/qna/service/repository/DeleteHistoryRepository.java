package nextstep.qna.service.repository;

import java.util.List;
import nextstep.qna.domain.DeleteHistory;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);
}
