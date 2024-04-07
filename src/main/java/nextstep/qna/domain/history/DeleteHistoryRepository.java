package nextstep.qna.domain.history;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(final List<DeleteHistory> deleteHistories);
}
