package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionState;
import nextstep.sessions.domain.SessionTypeFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "INSERT INTO class_session (title, state, capacity, amount, start_date, end_date, created_at) VALUES(?, ?, ?, ?, ?, ? ,?)";

        return jdbcTemplate.update(sql, ps -> preparedStatementSetter(ps, session));
    }

    @Override
    public void saveAll(List<Session> sessions) {
        String sql = "INSERT INTO class_session (title, state, capacity, amount, start_date, end_date, created_at) VALUES(?, ?, ?, ?, ?, ? ,?)";

        jdbcTemplate.batchUpdate(sql, sessions, sessions.size(), (ps, session) -> preparedStatementSetter(ps, session));
    }

    private void preparedStatementSetter(PreparedStatement ps, Session session) throws SQLException {
        ps.setString(1, session.getTitle());
        ps.setString(2, session.getState().name());
        ps.setInt(3, session.getCapacity());
        ps.setLong(4, session.getAmount());
        ps.setTimestamp(5, Timestamp.valueOf(session.getStartDate()));
        ps.setTimestamp(6, Timestamp.valueOf(session.getEndDate()));
        ps.setTimestamp(7, Timestamp.valueOf(session.getCreatedAt()));
    }

    @Override
    public Optional<Session> findById(long sessionId) {
        String sql = "SELECT id, title, state, capacity, amount, start_date, end_date, created_at FROM class_session WHERE id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> buildSession(rs), sessionId));
    }

    @Override
    public List<Session> findByIds(List<Long> ids) {
        String sql = "SELECT id, title, state, capacity, amount, start_date, end_date, created_at FROM class_session WHERE id IN (%s)";
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));

        return jdbcTemplate.query(String.format(sql, inSql), ids.toArray(), (rs, rowNum) -> buildSession(rs));
    }

    private Session buildSession(ResultSet rs) throws SQLException {
        return Session.builder()
                .id(rs.getLong(1))
                .title(rs.getString(2))
                .state(SessionState.valueOf(rs.getString(3)))
                .sessionType(SessionTypeFactory.of(rs.getInt(4), rs.getLong(5)))
                .startDate(rs.getTimestamp(6).toLocalDateTime())
                .endDate(rs.getTimestamp(7).toLocalDateTime())
                .createdAt(rs.getTimestamp(8).toLocalDateTime())
                .build();
    }
}
