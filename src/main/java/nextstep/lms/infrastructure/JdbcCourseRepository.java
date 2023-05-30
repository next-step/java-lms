package nextstep.lms.infrastructure;

import nextstep.lms.domain.Course;
import nextstep.lms.domain.LmsUser;
import nextstep.lms.repository.CourseRepository;
import nextstep.lms.repository.LmsUserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;
    private final LmsUserRepository lmsUserRepository;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate, LmsUserRepository lmsUserRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.lmsUserRepository = lmsUserRepository;
    }

    @Override
    public int save(Course course) {
        String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt());
    }

    @Override
    public Course findById(Long courseId) {
        String sql = "SELECT * FROM course WHERE id = ?";
        Course course = jdbcTemplate.queryForObject(sql, new Object[]{courseId}, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            Long creatorId = rs.getLong("creator_id");
            LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
            LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

            LmsUser creator = lmsUserRepository.findById(creatorId);

            Course loadedCourse = new Course(id, title, creator, new ArrayList<>(), createdAt, updatedAt);
            return loadedCourse;
        });
        return course;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
