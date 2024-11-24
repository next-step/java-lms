package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcEnrollmentRepository.class);
    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Set<Student> findEnrolledStudentsBySessionId(long sessionId) {
        String sql = "SELECT e.ns_user_id, e.session_id, e.enrollment_status " +
                "FROM enrollment e JOIN ns_user us ON e.ns_user_id = us.id WHERE session_id = ?";

        List<Student> enrolledStudents = jdbcTemplate.query(sql, rowMapper, sessionId);

        return new HashSet<>(enrolledStudents);
    }

    private final RowMapper<Student> rowMapper = (rs, rowNum) -> Student.of(
            rs.getLong(1),
            rs.getLong(2),
            EnrollmentStatus.valueOf(rs.getString(3))
    );

    @Override
    public void save(long sessionId, Student student) {
        log.info("[save] enrollmentStatus {}", student.getEnrollmentStatus());
        String sql = "INSERT INTO enrollment (session_id, ns_user_id, enrollment_status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, sessionId, student.getNsUserId(), student.getEnrollmentStatus().name());
    }

    @Override
    public void updateEnrollmentStatus(long sessionId, Student student) {
        String sql = "UPDATE enrollment SET enrollment_status = ? WHERE session_id = ? AND ns_user_id = ?";
        jdbcTemplate.update(sql, student.getEnrollmentStatus().name(), sessionId, student.getNsUserId());
    }

}