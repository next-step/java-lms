package nextstep.session.infrastructure;

import nextstep.session.domain.Enrollment;
import nextstep.session.domain.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static nextstep.common.domain.utils.JdbcConvertUtils.toLocalDateTime;
import static nextstep.common.domain.utils.JdbcConvertUtils.toTimestamp;

@Repository
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Enrollment enrollment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into session_enrollment (created_at, updated_at, user_id, session_id, approved) values(?,?,?,?,?)";
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setTimestamp(1, toTimestamp(enrollment.getCreatedAt()));
                    ps.setTimestamp(2, toTimestamp(enrollment.getUpdatedAt()));
                    ps.setLong(3, enrollment.getStudentId());
                    ps.setLong(4, enrollment.getSessionId());
                    ps.setBoolean(5, enrollment.isApproved());
                    return ps;
                },
                keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    @Override
    public Enrollment findById(Long id) {
        String sql = "select id, created_at, updated_at, user_id, session_id, approved from session_enrollment where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Enrollment(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                rs.getLong(4),
                rs.getLong(5),
                rs.getBoolean(6)
        ), id);
    }

    @Override
    public List<Enrollment> findAllBySessionId(Long sessionId) {
        String sql = "select id, created_at, updated_at, user_id, session_id, approved from session_enrollment where session_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Enrollment(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                rs.getLong(4),
                rs.getLong(5),
                rs.getBoolean(6)
        ), sessionId);
    }

    @Override
    public void update(Enrollment enrollment) {
        String sql = "update session_enrollment (id, created_at, updated_at, user_id, session_id, approved) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, enrollment.getId(), enrollment.getCreatedAt(), enrollment.getUpdatedAt(), enrollment.getStudentId(), enrollment.getSessionId(), enrollment.isApproved());
    }

    @Override
    public void delete(Enrollment enrollment) {
        String sql = "delete session_enrollment where id = ?";
        jdbcTemplate.update(sql, enrollment.getId());
    }
}
