package nextstep.qna.infrastructure;

import nextstep.qna.domain.deleteHistory.DeleteHistory;
import nextstep.qna.domain.deleteHistory.DeleteHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {

    }
}
