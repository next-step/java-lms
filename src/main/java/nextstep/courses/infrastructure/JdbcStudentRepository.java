package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Student student) {
        String sql = "insert into student (enrolment_id, ns_user_id, selection_status) values(?, ?, ?)";
        jdbcTemplate.update(sql, student.getEnrolmentId(), student.getNsUserId(), student.getSelectionStatus().toString());
    }

    @Override
    public Students findAllByEnrolment(Long id) {
        String sql = "select * from student where enrolment_id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3),
            SelectionStatus.valueOf(rs.getString(4))
        );

        List<Student> students = jdbcTemplate.query(sql, rowMapper, id);
        return new Students(students);
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        String sql = "select * from student where id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3),
            SelectionStatus.valueOf(rs.getString(4))
        );

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, studentId));
    }
}
