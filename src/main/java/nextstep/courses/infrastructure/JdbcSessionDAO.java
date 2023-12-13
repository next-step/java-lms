package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionDAO")
public class JdbcSessionDAO implements SessionDAO {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionDAO(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Session session) {
        String sql = "insert into session " +
                "(course_id, generation, started_at, finished_at, created_at, session_status, amount, max_user) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.courseId());
            ps.setLong(2, session.generation());
            ps.setObject(3, session.sessionPeriod().startedAt());
            ps.setObject(4, session.sessionPeriod().finishedAt());
            ps.setObject(5, LocalDateTime.now());
            ps.setString(6, session.sessionStatus().name());
            ps.setLong(7, session.sessionPaymentCondition().amount());
            ps.setLong(8, session.sessionPaymentCondition().maxUserNumber());
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, generation, started_at, finished_at, session_status, amount, max_user from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(4)), toLocalDateTime(rs.getTimestamp(5))),
                rs.getString(6),
                new SessionCondition(rs.getLong(7), rs.getLong(8))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<NsUserSession> getNsUserSessions(Long sessionId) {
        String sql = "select session_id, ns_user_id from ns_user_session where session_id=?";
        RowMapper<NsUserSession> rowMapper = (rs, rowNum) -> new NsUserSession(rs.getLong(1), rs.getLong(2));
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int saveNsUserSession(NsUserSession nsUserSession) {
        String sql = "insert into ns_user_session(session_id, ns_user_id) values (?, ?)";
        return jdbcTemplate.update(sql, nsUserSession.sessionId(), nsUserSession.nsUserId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
