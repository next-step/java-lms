package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Course course) {
        String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, course.getTitle());
            ps.setLong(2, course.getCreatorId());
            ps.setTimestamp(3, Timestamp.valueOf(course.getCreatedAt()));
            return ps;
        }, keyHolder);

//        Long courseId = Objects.requireNonNull(keyHolder.getKey()).longValue();
//
//        if (course.getSessions() != null) {
//            saveSessions(courseId, course.getSessions());
//        }
//
//        return courseId;
        return keyHolder.getKey().longValue();
    }

//    private void saveSessions(Long courseId, Sessions sessions) {
//        for (int i = 1; i <= sessions.size(); i++) {
//            Session session = sessions.findByCohort(i);
//            if (session != null) {
//                sessionRepository.save(courseId, session);
//            }
//        }
//    }


    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> {
            Course course = new Course(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    toLocalDateTime(rs.getTimestamp(4)),
                    toLocalDateTime(rs.getTimestamp(5)));
            return course;
        };
//        Course course = jdbcTemplate.queryForObject(sql, rowMapper, id);
//
//        return new Course(course.getId(), course.getTitle(), course.getCreatorId(), course.getCreatedAt(), null, sessionRepository.findByCourseId(id));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
