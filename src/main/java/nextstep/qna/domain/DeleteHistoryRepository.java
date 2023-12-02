package nextstep.qna.domain;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeletedHistory> deleteHistories);
}
