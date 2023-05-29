package nextstep.qna.domain;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);
    void saveAll2(DeleteHistories deleteHistories);
}
