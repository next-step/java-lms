package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.type.SessionStatus;
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
    public int save(Session session) {
        String sql = "insert into session (title, start_date_time, end_date_time, status, is_free, money, max_participants, course_id) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.title(), session.startDateTime(), session.endDateTime(), session.status(), session.isFree(), session.money(), session.maxParticipants(), session.course());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, start_date_time, end_date_time, status, is_free, money, max_participants, course_id from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            Long sessionId = rs.getLong(1);
            String title = rs.getString(2);
            LocalDateTime startDateTime = toLocalDateTime(rs.getTimestamp(3));
            LocalDateTime endDateTime = toLocalDateTime(rs.getTimestamp(4));
            String status = rs.getString(5);
            boolean isFree = rs.getBoolean(6);
            int money = rs.getInt(7);
            int maxParticipants = rs.getInt(8);
            Long courseId = rs.getLong(9);
            return new Session(sessionId, title, SessionPeriod.of(startDateTime, endDateTime),
                    Price.of(isFree, money, ParticipantManager.of(maxParticipants)), SessionStatus.valueOf(status), courseId);
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
