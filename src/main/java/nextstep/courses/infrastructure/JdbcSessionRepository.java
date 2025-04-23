package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate)
                .withTableName("sessions")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public int save(Session session) {
        SessionMeta meta = session.getMeta();

        Map<String, Object> params = new HashMap<>();
        params.put("course_id", session.getCourseId());
        params.put("session_type", meta.getSessionType().name());
        params.put("start_date", meta.getStartAt());
        params.put("end_date", meta.getEndAt());
        params.put("price", meta.getPrice());
        params.put("cover_image_file_size", meta.getImageSize());
        params.put("cover_image_file_type", meta.getImageType());
        params.put("cover_image_width", meta.getImageWidth());
        params.put("cover_image_height", meta.getImageHeight());
        params.put("session_status", session.getStatus().name());
        params.put("capacity_max", session.isFree() ? null : session.getMax());
        params.put("capacity_current", session.getCurrent());
        params.put("created_at", session.getCreatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return key.intValue();
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
