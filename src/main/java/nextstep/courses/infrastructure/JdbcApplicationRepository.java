package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("applicationRepository")
public class JdbcApplicationRepository implements ApplicationRepository {
    private final JdbcOperations jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcApplicationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate)
                .withTableName("applications")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public int save(Application application) {
        Map<String, Object> params = new HashMap<>();
        params.put("session_id", application.getSessionId());
        params.put("user_id", application.getUserId());
        params.put("status", application.getStatus().name());
        params.put("createdAt", application.getCreatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return key.intValue();
    }

    @Override
    public Application findById(Long id) {
        String sql = "SELECT " +
                "id, session_id, user_id, status, created_at, updated_at " +
                "FROM applications WHERE id = ?";
        RowMapper<Application> rowMapper = (rs, rowNum) -> {
            ApplicationStatus status = ApplicationStatus.valueOf(rs.getString("status"));

            return new Application(
                    rs.getLong("id"),
                    rs.getLong("session_id"),
                    rs.getLong("user_id"),
                    status,
                    toLocalDateTime(rs.getTimestamp("created_at")),
                    toLocalDateTime(rs.getTimestamp("updated_at"))
            );
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
