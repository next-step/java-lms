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

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(2)), toLocalDateTime(rs.getTimestamp(3))),
                PaymentType.valueOf(rs.getString(4)),
                SessionStatus.valueOf(rs.getString(5)),
                rs.getInt(6),
                new SessionImageUrl(rs.getString(7))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select id, started_at, end_at, payment_type, status, maximum_user_count, image_url from session where course_id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(2)), toLocalDateTime(rs.getTimestamp(3))),
                PaymentType.valueOf(rs.getString(4)),
                SessionStatus.valueOf(rs.getString(5)),
                rs.getInt(6),
                new SessionImageUrl(rs.getString(7))
        );
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
    public List<NsUser> findAllUserBySessionId(Long sessionId) {
        String sql = "select ns.id, ns.user_id, ns.password, ns.name, ns.email, ns.created_at, ns.updated_at " +
                "from ns_user ns inner join session_user su on (ns.id = su.user_id) " +
                "where su.session_id = ?";

        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private Timestamp toTimeStamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
