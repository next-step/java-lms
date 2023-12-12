package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.type.ImageExtension;
import nextstep.courses.type.MaxRegister;
import nextstep.courses.type.SessionDuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    private final CourseRepository courseRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CourseRepository courseRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.courseRepository = courseRepository;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session(\n" +
                "                    id, course_id,\n" +
                "                    state,\n" +
                "                    cover_image_file_path, image_type, image_capacity, image_width, image_height,\n" +
                "                    start_time, end_time,\n" +
                "                    max_user_count,\n" +
                "                    fee\n" +
                ")\n" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        RawSession db = new RawSession(session);
        return jdbcTemplate.update(sql,
                db.id(),
                db.courseId(),
                db.startTime(),
                db.coverImageFilePath(), db.imageType(), db.imageCapacity(), db.imageWidth(), db.imageHeight(),
                db.startTime(), db.endTime(),
                db.maxUserCount(),
                db.fee());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, state, cover_image_file_path, image_type, image_capacity, image_width, image_height, start_time, end_time, fee\n" +
                "from session\n" +
                "where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            ImageExtension extension = ImageExtension.valueOf(rs.getString(5));
            MaxRegister maxUserCount = MaxRegister.of(rs.getInt(11));
            if(rs.wasNull()) {
                maxUserCount = MaxRegister.infinite();
            }

            return Session.createPaidSession(
                    rs.getLong(1),
                    courseRepository.findById(rs.getLong(2)),
                    new SessionImage(rs.getString(4), rs.getInt(6), rs.getInt(7), rs.getInt(8), extension),
                    new SessionDuration(toLocalDateTime(rs.getTimestamp(9)), toLocalDateTime(rs.getTimestamp(10))),
                    maxUserCount,
                    rs.getInt(11)
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

/**
 * 세션 객체를 DB 레코드로 매핑하는 책임을 가진 객체
 * 객체를 탐색하며 DB에 매핑될 필드를 찾아냅니다.
 */
class RawSession {
    private final Session original;

    public RawSession(Session original) {
        this.original = original;
    }

    public Long id() {
        return original.getId();
    }

    public Long courseId() {
        return original.getCourse().getId();
    }

    public String state() {
        return original.getState().toString();
    }

    public String coverImageFilePath() {
        return original.getCoverImage().getFilePath();
    }

    public String imageType() {
        return original.getCoverImage().getType().toString();
    }

    public int imageCapacity() {
        return original.getCoverImage().getCapacity().toInt();
    }

    public int imageWidth() {
        return original.getCoverImage().getSize().getWidth();
    }

    public int imageHeight() {
        return original.getCoverImage().getSize().getHeight();
    }

    public LocalDateTime startTime() {
        return original.getDuration().getStartTime();
    }

    public LocalDateTime endTime() {
        return original.getDuration().getEndTime();

    }

    public Integer maxUserCount() {
        if (original.getMaxUserCount().isInfinite()) {
            return null;
        }

        return original.getMaxUserCount().toInt();
    }

    public int fee() {
        return original.getFee();
    }

}
