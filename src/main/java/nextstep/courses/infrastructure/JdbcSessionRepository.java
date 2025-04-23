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
import java.util.List;
import java.util.Map;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final SimpleJdbcInsert imageInsert;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate)
                .withTableName("sessions")
                .usingGeneratedKeyColumns("id");
        this.imageInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate)
                .withTableName("session_images")
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
        params.put("lecture_status", session.getLectureStatus().name());
        params.put("recruitment_status", session.getRecruitmentStatus().name());
        params.put("capacity_max", session.isFree() ? null : session.getMax());
        params.put("capacity_current", session.getCurrent());
        params.put("created_at", session.getCreatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        Long sessionId = key.longValue();

        for (NsImage image : meta.getImages()) {
            Map<String, Object> imageParams = new HashMap<>();
            imageParams.put("session_id", sessionId);
            imageParams.put("file_size", image.getSize());
            imageParams.put("file_type", image.getType());
            imageParams.put("width", image.getWidth());
            imageParams.put("height", image.getHeight());
            imageInsert.execute(imageParams);
        }

        return sessionId.intValue();
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT " +
                "id, course_id, session_type, start_date, end_date, price, " +
                "lecture_status, recruitment_status, capacity_max, capacity_current, " +
                "created_at, updated_at " +
                "FROM sessions WHERE id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            SessionType sessionType = SessionType.valueOf(rs.getString("session_type"));
            SessionPeriod period = new SessionPeriod(
                    rs.getDate("start_date").toLocalDate(), rs.getDate("end_date").toLocalDate()
            );
            Price price = sessionType.isFree() ? Price.free() : Price.of(rs.getLong("price"));
            LectureStatus lectureStatus = LectureStatus.valueOf(rs.getString("lecture_status"));
            RecruitmentStatus recruitmentStatus = RecruitmentStatus.valueOf(rs.getString("recruitment_status"));

            List<NsImage> images = jdbcTemplate.query(
                    "SELECT file_size, file_type, width, height FROM session_images WHERE session_id = ?",
                    (rs2, rn) -> new NsImage(
                            rs2.getLong("file_size"),
                            rs2.getString("file_type"),
                            rs2.getInt("width"),
                            rs2.getInt("height")
                    ),
                    rs.getLong("id")
            );

            SessionMeta meta = new SessionMeta(sessionType, period, price, images);

            Capacity capacity = sessionType.isFree() ?
                    new UnlimitedCapacity(rs.getInt("capacity_current")) :
                    new LimitedCapacity(rs.getInt("capacity_max"), rs.getInt("capacity_current"));

            return new Session(
                    rs.getLong("id"),
                    rs.getLong("course_id"),
                    meta,
                    lectureStatus,
                    recruitmentStatus,
                    capacity,
                    toLocalDateTime(rs.getTimestamp("created_at")),
                    toLocalDateTime(rs.getTimestamp("updated_at"))
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
