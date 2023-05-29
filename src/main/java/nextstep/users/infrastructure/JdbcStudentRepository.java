package nextstep.users.infrastructure;

import nextstep.users.domain.Student;
import nextstep.users.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcStudentRepository implements StudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student(ns_user_id, session_id) values (?, ?);";
        return jdbcTemplate.update(sql, student.getNsUserId(), student.getSessionId());
    }

    @Override
    public Student findById(long id) {
        String sql = "select id, ns_user_id, session_id from student where id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
