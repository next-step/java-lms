package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import nextstep.utils.FileImage;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, title, start_at, end_at, cover_image_path, price_type, price, max_attendees, state, create_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getCourseId(), session.getTitle(), session.getStartAt(), session.getEndAt(), session.getCoverImage().getPath(), session.getPriceType().name(), session.getPrice(), session.getmaxAttendees());
    }

    @Override
    public Session findById(Long id) {
        String sessionSql = "select id, course_id, title, start_at, end_at, cover_image_path, price_type, price, max_attendees, state, create_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getTimestamp(4).toLocalDateTime(),
                rs.getTimestamp(5).toLocalDateTime(),
                new CoverImage(new FileImage(rs.getString(6))),
                PriceType.valueOf(rs.getString(7)),
                rs.getInt(8),
                rs.getInt(9),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11))
        );
        Session session = jdbcTemplate.queryForObject(sessionSql, rowMapper, id);
        JdbcSessionAttendeeRepository sessionAttendeeRepository = new JdbcSessionAttendeeRepository(jdbcTemplate);
        List<String> attendeeIds = sessionAttendeeRepository.findBySessionId(id);
        UserRepository userRepository = new JdbcUserRepository(jdbcTemplate);
        attendeeIds.forEach(attendeeId -> {
            NsUser user = userRepository.findByUserId(attendeeId).get();
            session.apply(user);
        });
        return session;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
