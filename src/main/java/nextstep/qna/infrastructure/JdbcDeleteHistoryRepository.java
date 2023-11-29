package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeleteHistories;
import nextstep.qna.domain.DeleteHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(DeleteHistories deleteHistories) {

    }
}
