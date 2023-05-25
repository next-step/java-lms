package nextstep.qna.domain;

import java.util.List;

public interface DeleteHistoryRepository {

    void save(DeleteHistory deleteHistory);
    void saveAll(List<DeleteHistory> deleteHistories);
}
