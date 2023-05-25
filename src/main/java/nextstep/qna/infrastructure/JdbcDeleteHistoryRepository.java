package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void save(DeleteHistory deleteHistory) {

    }

    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {

    }
}
