package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.qna.domain.DeleteHistorys;
import org.springframework.stereotype.Repository;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(DeleteHistorys deleteHistories) {

    }
}
