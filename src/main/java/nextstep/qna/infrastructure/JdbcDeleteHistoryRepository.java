package nextstep.qna.infrastructure;

import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.users.domain.UserCode;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcDeleteHistoryRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //@Override
    public void saveAllV1(List<DeleteHistory> deleteHistories) {
        String sql = "INSERT INTO delete_history (content_type, content_id, created_at, deleted_by_user_code) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                DeleteHistory deleteHistory = deleteHistories.get(i);
                ps.setString(1, deleteHistory.getContentType().name());
                ps.setLong(2, deleteHistory.getContentId());
                ps.setTimestamp(3, Timestamp.valueOf(deleteHistory.getCreatedAt()));
                ps.setString(4, deleteHistory.getDeletedBy().value());
            }
            @Override
            public int getBatchSize() {
                return deleteHistories.size();
            }
        });
    }

    @Override
    public void saveAll(List<DeleteHistory> deleteHistories) {
        saveAllV1(deleteHistories);
        //saveAllV2(deleteHistories);
    }

    @Deprecated(since = "좀더 효율적일꺼같은데..")
    public void saveAllV2(List<DeleteHistory> deleteHistories) {
        String sql = "INSERT INTO delete_history (content_type, content_id, created_at, deleted_by_user_code) " +
                "VALUES (:contentType, :contentId, :createdAt, :deletedBy)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(deleteHistories.toArray());
        namedParameterJdbcTemplate.batchUpdate(sql, batchParams);
    }

    private RowMapper<DeleteHistory> rowMapper() {
        return ((rs, rowNum) -> {
            return new DeleteHistory(
                    rs.getLong("delete_history_id"),
                    ContentType.of(rs.getString("content_type")),
                    rs.getLong("content_id"),
                    UserCode.of(rs.getString("created_date")),
                    rs.getTimestamp("deleted_by_user_code").toLocalDateTime()
            );
        });

    }

    @Override
    public List<DeleteHistory> findAll() {
        return jdbcTemplate.query("SELECT * FROM delete_history", rowMapper());
    }

    @Override
    public Long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(delete_history_id) FROM delete_history", Long.class);
    }
}
