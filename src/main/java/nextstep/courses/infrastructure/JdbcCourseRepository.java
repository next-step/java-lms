package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    private final FreeSessionRepository freeSessionRepository;
    private final PaySessionRepository paySessionRepository;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate, FreeSessionRepository freeSessionRepository, PaySessionRepository paySessionRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.freeSessionRepository = freeSessionRepository;
        this.paySessionRepository = paySessionRepository;
    }

    @Override
    public int save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, course.getTitle());
            ps.setLong(2, course.getCreatorId());
            ps.setTimestamp(3, Timestamp.valueOf(course.getCreatedAt()));

            return ps;
        }, keyHolder);

        course.getSessions().getSessions().forEach(session -> {
                    if (session instanceof PaySession) {
                        paySessionRepository.saveSession((PaySession) session, (long) keyHolder.getKey());
                    }

                    if (session instanceof FreeSession) {
                        freeSessionRepository.saveSession((FreeSession) session, (long) keyHolder.getKey());
                    }
                }
        );

        return result;
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        Sessions sessions = Sessions.concatSessions(freeSessionRepository.findByCourseId(id), paySessionRepository.findByCourseId(id));

        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                sessions);

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
