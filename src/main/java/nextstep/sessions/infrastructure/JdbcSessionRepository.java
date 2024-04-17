package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.image.Capacity;
import nextstep.sessions.domain.image.Image;
import nextstep.sessions.domain.image.ImageSize;
import nextstep.sessions.domain.image.ImageType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class JdbcSessionRepository implements SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Session> findById(long id) {
        String sql = "SELECT id, started_at, ended_at, session_name, image_id, session_register_details_id FROM session WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long sessionId = rs.getLong("id");
            LocalDateTime startedAt = rs.getTimestamp("started_at").toLocalDateTime();
            LocalDateTime endedAt = rs.getTimestamp("ended_at").toLocalDateTime();
            String sessionName = rs.getString("session_name");
            long imageId = rs.getLong("image_id");
            long sessionRegisterDetailsId = rs.getLong("session_register_details_id");
            return new Session(sessionId,
                    startedAt,
                    endedAt,
                    sessionName,
                    new Image(imageId, new Capacity(500), ImageType.JPG, new ImageSize(300, 200)),
                    new SessionRegisterDetails(sessionRegisterDetailsId, null, null, null, null));
        }, id));
    }

    @Override
    public int save(Session session) {
        String sql = "INSERT INTO session (id, started_at, ended_at, session_name, image_id, session_register_details_id) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getId(), session.getStartedAt(), session.getEndedAt(), session.getSessionName(), session.getImageId(), session.getSessionRegisterDetailsId());
    }
}
