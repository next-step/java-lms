package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final Enrollment enrollment) {

        String sql = "insert into enrollment (user_id, session_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.nsUserId(), enrollment.sessionId(), enrollment.createdAt());
    }

    @Override
    public int update(final Enrollment enrollment) {
        String sql = "update enrollment SET approved = ? where id = ?";
        return jdbcTemplate.update(sql, toIntApproved(enrollment.getApproved()), enrollment.id());
    }

    @Override
    public Optional<Enrollment> findById(final Long id) {
        String sql = "select id, user_id, session_id, approved, created_at, updated_at from enrollment where id = ?";
        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                toApproved(rs.getInt(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));
        return  Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private boolean toApproved(int intApproved) {
        if (intApproved == 1) {
            return true;
        }

        return false;
    }

    private int toIntApproved(boolean approved) {
        if (approved) {
            return 1;
        }

        return 0;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime();
    }
}
