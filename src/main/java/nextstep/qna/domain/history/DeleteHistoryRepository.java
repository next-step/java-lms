package nextstep.qna.domain.history;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);
}
