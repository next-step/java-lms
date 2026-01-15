package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.enrollment.Enrollment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Enrollment enrollment) {
        String sql = "insert into enrollment (session_id, user_id, created_at) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, enrollment.getSessionId());
            ps.setLong(2, enrollment.getUserId());
            ps.setTimestamp(3, Timestamp.valueOf(enrollment.getCreatedAt()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalStateException("수강신청 저장 중 키를 가져올 수 없습니다.");
        }
        return key.longValue();
    }

    @Override
    public List<Enrollment> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, user_id, created_at from enrollment where session_id = ?";
        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("user_id"),
            toLocalDateTime(rs.getTimestamp("created_at")),
            null
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
