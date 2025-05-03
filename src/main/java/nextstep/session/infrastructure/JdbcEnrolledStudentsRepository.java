package nextstep.session.infrastructure;

import nextstep.session.domain.student.EnrolledStudent;
import nextstep.session.domain.student.EnrolledStudents;
import nextstep.session.domain.student.EnrolledStudentsRepository;
import nextstep.session.domain.student.EnrollmentStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("enrolledStudentsRepository")
public class JdbcEnrolledStudentsRepository implements EnrolledStudentsRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrolledStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(EnrolledStudents enrolledStudents) {
        if (enrolledStudents == null || enrolledStudents.isEmpty()) {
            return 0;
        }

        String sql = "insert into enrolled_students (session_id, student_id, enrollment_status) values(?, ?, ?)";
        for(EnrolledStudent student : enrolledStudents.getStudents()) {
            jdbcTemplate.update(sql,
                    enrolledStudents.getSessionId(),
                    student.getStudentId(),
                    student.getEnrollmentStatus().toString());
        }

        return enrolledStudents.getStudents().size();
    }

    @Override
    public EnrolledStudents findBySessionId(Long sessionId) {
        String sql = "select student_id, enrollment_status " +
                "from enrolled_students " +
                "where session_id = ?";
        RowMapper<EnrolledStudent> rowMapper = (rs, rowNum) -> {
            return new EnrolledStudent(
                    rs.getLong(1),
                    EnrollmentStatus.valueOf(rs.getString(2)));
        };

        List<EnrolledStudent> students = jdbcTemplate.query(sql, rowMapper, sessionId);

        return new EnrolledStudents(sessionId, students);
    }
}
