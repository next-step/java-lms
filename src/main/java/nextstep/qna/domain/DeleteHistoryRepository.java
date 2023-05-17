package nextstep.qna.domain;

import java.util.Collection;

public interface DeleteHistoryRepository {
    void saveAll(Collection<DeleteHistory> deleteHistories);
}
