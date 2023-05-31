package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (session_id)" +
                " values(?)";
        return jdbcTemplate.update(sql, student.getSessionId());
    }

    @Override
    public Student findById(Long studentId) {
        String sql = "select id, session_id"+
                " from student" +
                " where id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(rs.getLong(1), rs.getLong(2));

        return jdbcTemplate.queryForObject(sql, rowMapper, studentId);
    }

    @Override
    public List<Student> findBySessionId(Long sessionId) {
        String sql = "select id, session_id" +
                " from student" +
                " where session_id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2)
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
