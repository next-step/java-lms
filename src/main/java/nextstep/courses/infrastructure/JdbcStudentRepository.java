package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplates;

    public JdbcStudentRepository(JdbcOperations jdbcTemplates) {
        this.jdbcTemplates = jdbcTemplates;
    }

    @Override
    public Long save(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into student (name) values(?)";
        jdbcTemplates.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, student.getName());
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    @Override
    public Student findById(Long id) {
        String sql = "select id, name from student where id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getString(2));

        return jdbcTemplates.queryForObject(sql, rowMapper, id);
    }
}
