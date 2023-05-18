package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (bill_type, session_status, session_cover_image, max_user_count, started_at, ended_at, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, session.getSessionBillType().name(),
                                   session.getSessionStatus().name(),
                                   session.getSessionCoverImageUrl(),
                                   session.getMaxUserCount(),
                                   Timestamp.valueOf(session.getSessionPeriod().getStartedAt()),
                                   Timestamp.valueOf(session.getSessionPeriod().getEndedAt()),
                                   Timestamp.valueOf(session.getCreatedAt()));
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, bill_type, session_status, session_cover_image, max_user_count, started_at, ended_at, " +
                "created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) ->
                new Session(rs.getLong(1),
                            SessionBillType.find(rs.getString(2)),
                            SessionStatus.find(rs.getString(3)),
                            new SessionCoverImage(rs.getString(4)),
                            rs.getInt(5),
                            new SessionPeriod(toLocalDateTime(rs.getTimestamp(6)), toLocalDateTime(rs.getTimestamp(7))),
                            toLocalDateTime(rs.getTimestamp(8)),
                            toLocalDateTime(rs.getTimestamp(9))
                );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
