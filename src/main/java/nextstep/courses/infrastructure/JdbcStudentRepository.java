package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplates;

    public JdbcStudentRepository(JdbcOperations jdbcTemplates) {
        this.jdbcTemplates = jdbcTemplates;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (name) values(?)";
        return jdbcTemplates.update(sql, student.getName());
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
