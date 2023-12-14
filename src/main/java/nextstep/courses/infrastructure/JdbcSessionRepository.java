package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.infrastructure.exception.DbInsertFailException;
import nextstep.courses.type.ImageExtension;
import nextstep.courses.type.MaxRegister;
import nextstep.courses.type.SessionDuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    public long save(Session session) {
        String sql = "insert into session(\n" +
                "                    state,\n" +
                "                    course_id,\n" +
                "                    cover_image_file_path, image_type, image_capacity, image_width, image_height,\n" +
                "                    start_time, end_time,\n" +
                "                    max_user_count,\n" +
                "                    fee\n" +
                ")\n" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        RawSession db = new RawSession(session);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, db.state());
            ps.setLong(2, db.courseId());
            ps.setString(3, db.coverImageFilePath());
            ps.setString(4, db.imageType());
            ps.setInt(5, db.imageCapacity());
            ps.setInt(6, db.imageWidth());
            ps.setInt(7, db.imageHeight());
            ps.setTimestamp(8, Timestamp.valueOf(db.startTime()));
            ps.setTimestamp(9, Timestamp.valueOf(db.endTime()));
            ps.setInt(10, db.maxUserCount());
            ps.setInt(11, db.fee());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() == null) {
            throw new DbInsertFailException("Session");
        }
        return keyHolder.getKey().longValue();
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

            return Session.create(
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
