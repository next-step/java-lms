package nextstep.qna.domain;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);

    List<DeleteHistory> findAll();

    Long count();
}
