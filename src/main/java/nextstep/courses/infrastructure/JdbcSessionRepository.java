package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.util.Optional;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPayType;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import utils.LocalDateTimeUtils;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Session> singleSessionRowMapper = (rs, rowNum) -> new Session (
        rs.getLong("id"),
        rs.getLong("course_id"),
        SessionPayType.valueOf(rs.getString("session_pay_type")),
        SessionStatus.valueOf(rs.getString("session_status")),
        rs.getInt("capacity"),
        LocalDateTimeUtils.of(rs.getTimestamp("start_at")),
        LocalDateTimeUtils.of(rs.getTimestamp("finish_at"))
    );

    @Override
    public int save(Session session) {
        final String sql = "INSERT INTO session(course_id, session_pay_type, session_status, capacity, start_at, finish_at ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, pstmt -> {
            final SessionPeriod sessionPeriod = session.getSessionPeriod();
            pstmt.setLong(1, session.getCourseId());
            pstmt.setString(2, session.getSessionPayType().name());
            pstmt.setString(3, session.getSessionStatus().name());
            pstmt.setLong(4, session.getMaxCapacity());
            pstmt.setTimestamp(5, Timestamp.valueOf(sessionPeriod.getStartAt()));
            pstmt.setTimestamp(6, Timestamp.valueOf(sessionPeriod.getFinishAt()));
        });
    }

    @Override
    public Optional<Session> findById(Long id) {
        final String sql = "SELECT id, course_id, session_pay_type, session_status, capacity, start_at, finish_at FROM session WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, singleSessionRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
