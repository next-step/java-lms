package nextstep.qna.infrastructure;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcDeleteHistoryRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {
        final String sql = "insert into delete_history (content_id, content_type, created_date, deleted_by_id) values (?, ?, ?, ?)";
        deleteHistories.forEach(deleteHistory ->
                jdbcTemplate.update(sql, deleteHistory.getContentId(), deleteHistory.getContentType(),
                        deleteHistory.getCreatedDate(), deleteHistory.getDeletedBy()
                )
        );
    }
}
