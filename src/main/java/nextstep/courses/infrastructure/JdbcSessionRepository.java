package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionTime;
import nextstep.courses.domain.enums.ImageType;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.users.domain.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("jdbcSessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findBySessionId(long sessionId) {
        String sql = "SELECT * FROM session WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, sessionRowMapper(), sessionId);
    }

    @Override
    public long save(Session session, long courseId) {
        String sql = "INSERT INTO session (course_id, maximum_enrollment, period, image_id, opening_date_time, closing_date_time, session_status, session_type, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                courseId,
                session.getEnrollment().getMaximumEnrollment(),
                session.getPeriod(),
                session.getCoverImage().getId(),
                session.getSessionTime().getOpeningDateTime(),
                session.getSessionTime().getClosingDateTime(),
                session.getSessionStatus().getDescription(),
                session.getSessionType().getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Override
    public void enrollUser(Session session) {
        String sql = "INSERT INTO session_next_step_user (session_id, user_id, created_at) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, session.getId(), session.getEnrollment().getLatestEnrollmentUser().getId(), LocalDateTime.now());
    }

    @Override
    public List<User> findUsersBySessionId(long sessionId) {
        String sql = "SELECT u.* FROM next_step_user u INNER JOIN session_next_step_user su ON u.id = su.user_id WHERE su.session_id = ?";

        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    public Image findImageByImageId(long imageId) {
        String sql = "SELECT * FROM image WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, imageRowMapper(), imageId);
    }

    private LocalDateTime toLocalDateTime(Timestamp time) {
        if (time == null) {
            return null;
        }
        return time.toLocalDateTime();
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getString("period"),
                findImageByImageId(rs.getLong("image_id")),
                new SessionTime(toLocalDateTime(rs.getTimestamp("opening_date_time")), toLocalDateTime(rs.getTimestamp("closing_date_time"))),
                SessionType.valueOf(rs.getString("session_type")),
                SessionStatus.valueOf(rs.getString("session_status")),
                new Enrollment(findUsersBySessionId(rs.getLong("id")), rs.getInt("maximum_enrollment"))
        );
    }

    private RowMapper<Image> imageRowMapper() {
        return (rs, rowNum) -> {
            try {
                return new Image(
                        rs.getLong("id"),
                        rs.getString("name"),
                        new URI(rs.getString("uri")),
                        rs.getLong("size"),
                        ImageType.of(rs.getString("image_type"))
                );
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

}
