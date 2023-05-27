package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Session session, Long courseId) {
        String sql = "insert into session(started_at, end_at, payment_type, status, maximum_user_count, image_url, course_id) values(?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});

            ps.setTimestamp(1, toTimeStamp(session.getSessionPeriod().getStartedAt()));
            ps.setTimestamp(2, toTimeStamp(session.getSessionPeriod().getEndAt()));
            ps.setString(3, session.getPaymentType().getKey());
            ps.setString(4, session.getSessionStatus().getKey());
            ps.setInt(5, session.enrollmentCount());
            ps.setString(6, session.getSessionImageUrl().value());
            ps.setLong(7, courseId);

            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.longValue() : -1;
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "select id, started_at, end_at, payment_type, status, maximum_user_count, image_url from session where id = ?";

        RowMapper<Session> rowMapper = sessionRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select id, started_at, end_at, payment_type, status, maximum_user_count, image_url from session where course_id = ?";

        RowMapper<Session> rowMapper = sessionRowMapper();
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    @Override
    public long saveSessionUser(Session session, NsUser nextStepUser) {
        String sql = "insert into session_users(user_id, session_id) values(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.getId());
            ps.setLong(2, nextStepUser.getId());

            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.longValue() : -1;
    }

    @Override
    public List<String> findAllUserBySessionId(Long sessionId) {
        String sql = "select user_id from session_users where session_id = ?";

        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString(1);
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> new Session(
                rs.getLong(1),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(2)), toLocalDateTime(rs.getTimestamp(3))),
                PaymentType.valueOf(rs.getString(4)),
                SessionStatus.valueOf(rs.getString(5)),
                rs.getInt(6),
                new SessionImageUrl(rs.getString(7))
        );
    }

    private Timestamp toTimeStamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
