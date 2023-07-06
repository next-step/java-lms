package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (session_id, approval_state) values(?, ?)" ;
        return jdbcTemplate.update(sql, student.getSessionId(), student.getApprovalState());
    }

    @Override
    public Student findById(Long studentId) {
        String sql = "select id, session_id, approval_state from student where id = ?" ;

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3));

        return jdbcTemplate.queryForObject(sql, rowMapper, studentId);
    }

    @Override
    public List<Student> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, approval_state from student where session_id = ?" ;

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int updateApprovalState(Long sessionId, Long studentId, ApprovalState approvalState) {
        String sql = "update student set approval_state = ? where id = ? and session_id = ?" ;
        return jdbcTemplate.update(sql, approvalState.getCode(), studentId, sessionId);
    }
}
