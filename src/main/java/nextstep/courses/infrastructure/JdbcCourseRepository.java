package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.domain.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {

    private final SessionRepository sessionRepository;
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseRepository(SessionRepository sessionRepository, JdbcTemplate jdbcTemplate) {
        this.sessionRepository = sessionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Course course) {
        String sql = "insert into course (title, creator_id, created_at, updated_at) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), Timestamp.valueOf(course.getCreatedAt()), Timestamp.valueOf(course.getUpdatedAt()));
    }

    @Override
    public Course findById(Long id) {
        String sql = "SELECT * FROM course WHERE id = ?";
        Course course = jdbcTemplate.queryForObject(sql, courseRowMapper(), id);
        List<Session> sessions = sessionRepository.findSessionsByCourseId(id);
        for (Session session : sessions) {
            course.addSession(session);
        }
        return course;
    }

    @Override
    public int update(Course course) {
        String sql = "update course set title = ?, creator_id = ?, updated_at = ? where id = ?";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), Timestamp.valueOf(course.getUpdatedAt()), course.getId());
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from course where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private RowMapper<Course> courseRowMapper() {
        return (rs, rowNum) -> new Course(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("creator_id"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}