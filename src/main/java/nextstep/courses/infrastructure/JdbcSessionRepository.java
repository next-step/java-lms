package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionCoverImage;
import nextstep.courses.domain.SessionDuration;
import nextstep.courses.domain.SessionPaymentType;
import nextstep.courses.domain.SessionRegistration;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into " +
                "session (started_at, ended_at, cover_image, payment_type, status, student_capacity, created_at) " +
                "values(?, ?, ?, ?, ? ,? ,?)";
        return jdbcTemplate.update(sql,
                session.getStartedAt(),
                session.getEndedAt(),
                session.getCoverImageUrl(),
                session.getPaymentTypeName(),
                session.getStatusName(),
                session.getStudentCapacity(),
                session.getCreatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, " +
                "started_at, ended_at, cover_image, payment_type, status, student_capacity, created_at, updated_at " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                new SessionDuration(
                        toLocalDateTime(rs.getTimestamp(2)),
                        toLocalDateTime(rs.getTimestamp(3))),
                SessionCoverImage.create(rs.getString(4)),
                SessionPaymentType.find(rs.getString(5)),
                new SessionRegistration(
                        SessionStatus.find(rs.getString(6)),
                        rs.getInt(7)),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
