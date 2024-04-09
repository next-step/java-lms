package nextstep.common.domain;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistory> deleteHistories);
}
