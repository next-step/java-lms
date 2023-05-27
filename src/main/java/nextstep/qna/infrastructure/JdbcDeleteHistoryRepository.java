package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcDeleteHistoryRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {
        throw new RuntimeException();
    }

    @Override
    public List<DeleteHistory> findAll() {
        throw new RuntimeException();
    }

    @Override
    public Long count() {
        throw new RuntimeException();
    }
}
