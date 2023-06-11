package nextstep.courses.infrastructure;

import nextstep.courses.domain.registration.Student;
import nextstep.courses.domain.registration.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Student student) {
        String sql = "insert into student (user_id, session_id) values (?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, student.getNsUserId());
            ps.setLong(2, student.getSessionId());
            return ps;
        });
    }

    @Override
    public List<Student> findAllBySessionId(Long sessionId) {
        String sql = "select user_id, session_id from student where session_id = ? ";

        RowMapper<Student> rowMapper = ((rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2)
        ));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public Student findByUserId(Long nsUserId) {
        String sql = "select user_id, session_id, status from student where user_id = ?";

        RowMapper<Student> rowMapper = ((rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getBoolean(3)
        ));

        return jdbcTemplate.queryForObject(sql, rowMapper, nsUserId);
    }

    @Override
    public void updateStatus(Student student) {
        String sql = "update student set status = ? where user_id = ? ";

        jdbcTemplate.update(sql, student.getStatus(), student.getNsUserId());
    }

}
