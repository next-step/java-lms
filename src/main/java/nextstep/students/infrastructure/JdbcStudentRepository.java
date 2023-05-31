package nextstep.students.infrastructure;

import nextstep.students.domain.StudentRepository;
import nextstep.students.domain.Students;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Students students) {
        String sql = "insert into students (session_id, ns_user_id) values(?, ?)";
        return jdbcTemplate.update(sql, students.getSessionId(), students.getNsUserId());
    }
}
