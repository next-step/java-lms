package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import nextstep.utils.TimeUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Optional<Session> findById(Long id) {
        String sql = "select id, course_id, image_id, start_date, end_date, max_attendees, current_attendees, type, status from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session.Builder()
                .id(rs.getLong(1))
                .courseId(rs.getLong(2))
                .imageId(rs.getLong(3))
                .startDate(TimeUtils.toLocalDateTime(rs.getTimestamp(4)))
                .endDate(TimeUtils.toLocalDateTime(rs.getTimestamp(5)))
                .maxAttendees(rs.getInt(6))
                .currentAttendees(rs.getInt(7))
                .type(SessionType.valueOf(rs.getString(8)))
                .status(SessionStatus.valueOf(rs.getString(9)))
                .build();
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

}
