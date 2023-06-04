package nextstep.qna.repository;

import nextstep.qna.domain.history.DeleteHistory;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);
}
