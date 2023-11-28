package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import nextstep.courses.domain.Students;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (user_id, session_id, selection) values(?, ?, ?)";

        return jdbcTemplate.update(sql, student.getNsUserId(), student.getSessionId(), student.getSelection());
    }

    @Override
    public Students findBySessionId(long sessionId) {
        String sql = "select ns_user_id, session_id, selection from student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3));

        return new Students(jdbcTemplate.query(sql, rowMapper, sessionId));
    }

    @Override
    public Optional<Student> findByIdAndSessionId(long id,
                                                  long sessionId) {
        String sql = "select ns_user_id, session_id, selection from student where ns_user_id = ? and session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id, sessionId));
    }

    @Override
    public void updateSelection(Student student) {
        String sql = "update student set selection = ? where ns_user_id = ? and session_id = ?";

        jdbcTemplate.update(sql,
                student.getSelection(),
                student.getNsUserId(),
                student.getSessionId());
    }

}
