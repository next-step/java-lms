package nextstep.qna.infrastructure;

import java.util.List;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {

    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {

    }
}
