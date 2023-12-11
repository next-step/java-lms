package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, session_status, start_date_time, end_date_time, free, max_attendance from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                SessionStatus.valueOf(rs.getString(3)),
                rs.getTimestamp(4).toLocalDateTime(),
                rs.getTimestamp(5).toLocalDateTime(),
                rs.getBoolean(6),
                rs.getInt(7));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Long save(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "insert into session (course_id, session_status, start_date_time, end_date_time, free, max_attendance) values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, session.courseId());
            ps.setString(2, session.sessionStatus());
            ps.setTimestamp(3, Timestamp.valueOf(session.startDate()));
            ps.setTimestamp(4, Timestamp.valueOf(session.endDate()));
            ps.setBoolean(5, session.isFree());
            ps.setInt(6, session.maxAttendance());
            return ps;
        }, keyHolder);

        return (long) keyHolder.getKey();
    }
}
