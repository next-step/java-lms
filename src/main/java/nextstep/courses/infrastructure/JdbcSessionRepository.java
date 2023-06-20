package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, start_date, end_date, cover_image_url, is_free, registration_status, capacity, course_id from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new Period(
                        toLocalDateTime(rs.getTimestamp(3)),
                        toLocalDateTime(rs.getTimestamp(4))
                ),
                rs.getString(5),
                rs.getBoolean(6),
                new Registration(
                        RegistrationStatus.valueOf(rs.getString(7)),
                        new Students(),
                        rs.getLong(8)
                )
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
