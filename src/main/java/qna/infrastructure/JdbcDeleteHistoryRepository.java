package qna.infrastructure;

import org.springframework.stereotype.Repository;
import qna.domain.DeleteHistory;
import qna.domain.DeleteHistoryRepository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {

    }
}
