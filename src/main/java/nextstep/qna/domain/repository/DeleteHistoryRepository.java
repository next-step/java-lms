package nextstep.qna.domain.repository;

import nextstep.qna.domain.DeleteHistory;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);
}
