package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.courses.domain.course.session.Sessions;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcOperations jdbcTemplate;
    private final SessionRepository sessionRepository;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Override
    public Course save(Course course) {
        String sql = "insert into course " +
                "(title, ordering, creator_id, created_at) " +
                "values(?, ?, ?, ?)";

        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, course.title());
            ps.setInt(2, course.ordering());
            ps.setLong(3, course.creatorId());
            ps.setTimestamp(4, Timestamp.valueOf(course.createdAt()));
            return ps;
        }, keyHolder);

        Long courseId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return new Course(courseId, course.title(), course.ordering(), course.sessions(),
                course.creatorId(), course.createdAt(), course.updatedAt());
    }

    @Override
    public Course findById(Long id) {
        String sql = "select " +
                "id, title, ordering, creator_id, created_at, updated_at " +
                "from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                findAllByCourseId(rs.getLong(1)),
                rs.getLong(4),
                rs.getTimestamp(5).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(6)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private Sessions findAllByCourseId(Long id) {
        return this.sessionRepository.findAllByCourseId(id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
