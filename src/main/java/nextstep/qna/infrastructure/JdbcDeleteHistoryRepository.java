package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.service.repository.DeleteHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {

    }
}
