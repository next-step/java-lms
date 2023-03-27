package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeleteHistory;
import org.springframework.stereotype.Repository;
import nextstep.qna.domain.DeleteHistoryRepository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {

    }
}
