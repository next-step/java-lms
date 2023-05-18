package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(Collection<DeleteHistory> deleteHistories) {
    }
}
