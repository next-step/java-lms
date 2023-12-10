package nextstep.lms.infrastructure;

import nextstep.lms.domain.Student;
import nextstep.lms.domain.Students;
import nextstep.lms.repository.StudentsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository("studentsRepository")
public class JdbcStudentsRepository implements StudentsRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into students (user_id, session_id, status, created_at) values(?,?,?,?)";

        return jdbcTemplate.update(sql,
                student.getUserId(),
                student.getSessionId(),
                student.getStudentStatus(),
                LocalDateTime.now());
    }

    @Override
    public Students findBySession(Long sessionId) {
        String sql = "select user_id, session_id, status from students where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3));
        List<Student> sessionStudent = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new Students(sessionStudent);
    }

    @Override
    public int updateStatus(Student student) {
        String sql = "update students set status = ? where user_id = ? and session_id =?";

        return jdbcTemplate.update(sql,
                student.getStudentStatus(),
                student.getUserId(),
                student.getSessionId());
    }
}
