package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Course;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.infrastructure.entity.JdbcCourse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Course course) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("course")
                .usingGeneratedKeyColumns("id");

        Number number = simpleJdbcInsert.executeAndReturnKey(course.getParameters());
        course.setId(number.longValue());
        return number.longValue();
    }

    @Override
    public Course findById(Long id) {
        String sql = "select * from course where id = ?";
        JdbcCourse entity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcCourse.class), id);
        return entity == null ? null : entity.toDomain();
    }

}
