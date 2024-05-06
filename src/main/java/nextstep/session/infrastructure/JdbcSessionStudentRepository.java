package nextstep.session.infrastructure;

import java.util.List;
import nextstep.session.domain.SessionStudentRepository;
import nextstep.session.domain.Student;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionStudentRepository")
public class JdbcSessionStudentRepository implements SessionStudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAllEnrolledInSession(Long sessionId) {
        String sql = "select id, user_id, session_id, enrollment_approval_status from session_student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3),
            rs.getString(4)
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int save(Student student) {
        String sql = "insert into session_student(user_id, session_id, enrollment_approval_status) values(?,?,?)";
        return jdbcTemplate.update(sql, student.getUser_id(), student.getSession_id(),
            student.getApprovalStatus());
    }

    @Override
    public int updateStatus(Student student) {
        String sql = "update session_student set enrollment_approval_status=? where user_id=?";
        return jdbcTemplate.update(sql, student.getApprovalStatus(), student.getUser_id());
    }

    @Override
    public List<Student> findAllApprovedStudents(Long sessionId) {
        String sql = "select id, user_id, session_id, enrollment_approval_status from session_student where session_id = ? and enrollment_approval_status = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3),
            rs.getString(4)
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId, "승인");
    }
}
