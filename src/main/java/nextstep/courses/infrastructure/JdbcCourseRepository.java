package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.CourseUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Course course) {
        String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt());
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int saveUser(CourseUser courseUser) {
        String sql = "insert into course_user (course_id, user_id, status)" +
                " values(?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        return jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, courseUser.getCourseId());
            ps.setLong(2, courseUser.getUserId());
            ps.setBoolean(3, courseUser.isSelected());
            return ps;
        }, keyHolder);

    }

    @Override
    public CourseUser findByUserId(Long userId) {
        String sql = "select id, course_id, user_id, status from course_user where user_id = ? ";
        RowMapper<CourseUser> rowMapper = (rs, rowNum) -> new CourseUser(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getBoolean(4));

        try {
            CourseUser courseUser = jdbcTemplate.queryForObject(sql, rowMapper, userId);
            return courseUser;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
