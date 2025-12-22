package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.repository.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into enrollment (student_id, session_id) values (?, ?)";
        return jdbcTemplate.update(sql, enrollment.getStudentId(), enrollment.getSessionId());
    }

    @Override
    public Enrollment findById(Long id) {
        String sql = "select * from enrollment where id = ?";
        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
            rs.getLong("id"),
                rs.getLong("student_id"),
                rs.getLong("session_id")
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
