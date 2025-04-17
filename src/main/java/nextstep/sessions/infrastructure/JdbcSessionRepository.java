package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import nextstep.utils.TimeUtils;
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
    public int save(Session session) {
        String sql = "insert into session (id, course_id, image_id, start_date, end_date, max_attendees, type, status) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getId(), session.getCourseId(), session.getImageId(),
                session.getStartDate(), session.getEndDate(), session.getMaxAttendees(),
                session.getType().name(), session.getStatus().name());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, image_id, start_date, end_date, max_attendees, type, status from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                TimeUtils.toLocalDateTime(rs.getTimestamp(4)),
                TimeUtils.toLocalDateTime(rs.getTimestamp(5)),
                rs.getInt(6),
                SessionType.valueOf(rs.getString(7)),
                SessionStatus.valueOf(rs.getString(8)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
