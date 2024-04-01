package nextstep.qna.domain.deleteHistory;

import nextstep.qna.domain.deleteHistory.DeleteHistory;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);
}
