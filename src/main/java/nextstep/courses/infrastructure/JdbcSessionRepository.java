package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, status, start_date_time, end_date_time, free, max_attendance from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                SessionStatus.valueOf(rs.getString(2)),
                rs.getTimestamp(3).toLocalDateTime(),
                rs.getTimestamp(4).toLocalDateTime(),
                rs.getBoolean(5),
                rs.getInt(6));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void save(Session session) {
        String sql = "insert into session (course_id, cover_image_id, status, start_date_time, end_date_time, free, max_attendance) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, session.getCourse().getId(), session.getCoverImg().getId(),
                session.sessionStatus(), session.startDate(),
                session.endDate(), session.isFree(), session.maxAttendance());
    }
}
