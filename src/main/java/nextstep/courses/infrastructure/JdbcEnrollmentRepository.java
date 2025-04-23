package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Enrollment enrollment) {
    }

    @Override
    public Enrollments findByUserId(Long userId) {
        String sql = "SELECT * FROM enrollment WHERE student_id = ?";
        return new Enrollments(jdbcTemplate.query(sql, getEnrollmentRowMapper(), userId));
    }

    @Override
    public Enrollments findBySessionId(Long sessionId) {
        String sql = "SELECT * FROM enrollment WHERE session_id = ?";
        return new Enrollments(jdbcTemplate.query(sql, getEnrollmentRowMapper(), sessionId));
    }

    @Override
    public Enrollments findByStatus(EnrollmentStatus enrollmentStatus) {
        String sql = "SELECT * FROM enrollment WHERE status = ?";
        return new Enrollments(jdbcTemplate.query(sql, getEnrollmentRowMapper(), enrollmentStatus));
    }

    @Override
    public Enrollments findBySessionIdAndStatus(Long sessionId, EnrollmentStatus enrollmentStatus) {
        String sql = "SELECT * FROM enrollment WHERE session_id = ? AND status = ?";
        return new Enrollments(jdbcTemplate.query(sql, getEnrollmentRowMapper(), sessionId, enrollmentStatus));
    }

    private static RowMapper<Enrollment> getEnrollmentRowMapper() {
        return (rs, rowNumber) -> new Enrollment(
                rs.getLong("id"),
                new Session(rs.getLong("sessionId")),
                new Student(rs.getLong("student_id")),
                EnrollmentStatus.valueOf(rs.getString("status")),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
