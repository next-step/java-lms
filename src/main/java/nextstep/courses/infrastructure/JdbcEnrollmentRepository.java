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
    public Optional<Enrollments> findByUserId(Long userId) {
        String sql = "SELECT * FROM enrollment WHERE student_id = ?";

        List<Enrollment> enrollments = jdbcTemplate.query(sql, getEnrollmentRowMapper(), userId);
        if (enrollments.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new Enrollments(enrollments));
    }

    @Override
    public Optional<Enrollments> findBySessionId(Long sessionId) {
        String sql = "SELECT * FROM enrollment WHERE session_id = ?";

        List<Enrollment> enrollments = jdbcTemplate.query(sql, getEnrollmentRowMapper(), sessionId);
        if (enrollments.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new Enrollments(enrollments));
    }

    private static RowMapper<Enrollment> getEnrollmentRowMapper() {
        return (rs, rowNumber) -> new Enrollment(
                rs.getLong("id"),
                new Session(rs.getLong("sessionId")),
                new Student(rs.getLong("student_id")),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
