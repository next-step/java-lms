package nextstep.users.infrastructure;

import nextstep.courses.domain.registration.RegistrationStatus;
import nextstep.users.domain.Student;
import nextstep.users.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcStudentRepository implements StudentRepository {

    private JdbcOperations jdbcOperations;

    public JdbcStudentRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student(ns_user_id, session_id, registration_status) values (?, ?, ?);";
        return jdbcOperations.update(sql, student.getNsUserId(), student.getSessionId(), student.getRegistrationStatus());
    }

    @Override
    public Student findById(long id) {
        String sql = "select id, ns_user_id, session_id from student where id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                RegistrationStatus.valueOf(rs.getString(4)));
        return jdbcOperations.queryForObject(sql, rowMapper, id);
    }
}
