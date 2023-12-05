package nextstep.sessions.infrastructure;

import java.sql.*;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.repository.SessionRepository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findSessionBySessionId(int sessionId) {
        String sql = "select paid_type, fee, capacity, state, start_date, end_date from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> session(rs);
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    private Session session(ResultSet rs) throws SQLException {
        return new Session(
            PaidType.valueOf(rs.getString(1)),
            rs.getLong(2),
            rs.getInt(3),
            SessionState.valueOf(rs.getString(4)),
            toLocalDateTime(rs.getTimestamp(5)),
            toLocalDateTime(rs.getTimestamp(6))
        );
    }

    @Override
    public int saveSession(Session session) {
        String sql = "insert into session (paid_type, fee, capacity, state, start_date, end_date) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            session.paidType(),
            session.fee(),
            session.capacity(),
            session.sessionState(),
            Timestamp.valueOf(session.startDate()),
            Timestamp.valueOf(session.endDate())
        );
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
