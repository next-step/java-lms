package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "INSERT INTO sessions (" +
                "course_id, session_type, start_date, end_date, price, " +
                "cover_image_file_size, cover_image_file_type, cover_image_width, cover_image_height, " +
                "session_status, " +
                "capacity_max, capacity_current, " +
                "created_at" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SessionMeta meta = session.getMeta();

        return jdbcTemplate.update(sql,
                session.getCourseId(),
                meta.getSessionType().name(),
                meta.getStartAt(),
                meta.getEndAt(),
                meta.getPrice(),
                meta.getImageSize(),
                meta.getImageType(),
                meta.getImageWidth(),
                meta.getImageHeight(),
                session.getStatus().name(),
                session.isFree() ? null : session.getMax(),
                session.getCurrent(),
                session.getCreatedAt()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT " +
                "id, course_id, session_type, start_date, end_date, price, " +
                "cover_image_file_size, cover_image_file_type, cover_image_width, cover_image_height, " +
                "session_status, capacity_max, capacity_current, " +
                "created_at, updated_at " +
                "FROM sessions WHERE id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            SessionType sessionType = SessionType.valueOf(rs.getString(3));
            SessionStatus sessionStatus = SessionStatus.valueOf(rs.getString(11));
            SessionPeriod period = new SessionPeriod(
                    rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate()
            );
            Price price = sessionType.isFree() ? Price.free() : Price.of(rs.getLong(6));
            NsImage image = new NsImage(
                    rs.getLong(7), rs.getString(8),
                    rs.getInt(9), rs.getInt(10)
            );

            SessionMeta meta = new SessionMeta(sessionType, period, price, image);

            Capacity capacity = sessionType.isFree() ?
                    new UnlimitedCapacity(rs.getInt(13))
                    :
                    new LimitedCapacity(rs.getInt(12), rs.getInt(13));

            return new Session(
                    rs.getLong(1),
                    rs.getLong(2),
                    meta,
                    sessionStatus,
                    capacity,
                    toLocalDateTime(rs.getTimestamp(14)),
                    toLocalDateTime(rs.getTimestamp(15))
            );
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
