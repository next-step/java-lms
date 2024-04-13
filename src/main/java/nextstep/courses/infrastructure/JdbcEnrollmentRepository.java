package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into course (id, session_id, user_id, created_at, updated_at) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                enrollment.getId(),
                enrollment.getSessionId(),
                enrollment.getUserId(),
                enrollment.getCreatedAt(),
                enrollment.getUpdatedAt()
        );
    }

    @Override
    public Enrollment findById(Long id) {
        String sql = "select id, session_id, user_id, status, created_at, updated_at from course where id = ?";

        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                Enum.valueOf(EnrollmentStatus.class, rs.getString(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
