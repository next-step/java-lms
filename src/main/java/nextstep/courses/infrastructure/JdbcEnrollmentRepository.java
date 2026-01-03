package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.repository.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        String sql = "insert into enrollment (enrollment_status, student_id, session_id) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, enrollment.getEnrollmentStatus());
            ps.setLong(2, enrollment.getStudentId());
            ps.setLong(3, enrollment.getSessionId());
            return ps;
        }, keyHolder);

        long id = keyHolder.getKey().longValue();


        return new Enrollment(id, enrollment.getEnrollmentStatus(), enrollment.getStudentId(), enrollment.getSessionId());
    }

    @Override
    public void update(Enrollment enrollment) {
        String sql = "update enrollment set enrollment_status = ? where id = ?";

        jdbcTemplate.update(sql, enrollment.getEnrollmentStatus(), enrollment.getId());
    }

    @Override
    public Enrollment findById(long id) {
        String sql = "select * from enrollment where id = ?";
        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
            rs.getLong("id"),
                rs.getString("enrollment_status"),
                rs.getLong("student_id"),
                rs.getLong("session_id")
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Enrollment> findBySessionId(long sessionId) {
        String sql = "select * from enrollment where session_id = ?";

        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
                rs.getLong("id"),
                rs.getString("enrollment_status"),
                rs.getLong("student_id"),
                rs.getLong("session_id")
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }


}
