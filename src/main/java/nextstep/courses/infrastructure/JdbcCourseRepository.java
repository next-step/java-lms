package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Course;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.infrastructure.entity.JdbcCourse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Course course) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("course")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", course.getTitle());
        parameters.put("creator_id", course.getCreatorId());
        parameters.put("created_at", course.getCreatedAt());

        return simpleJdbcInsert.execute(parameters);
    }

    @Override
    public Course findById(Long id) {
        String sql = "select * from course where id = ?";
        JdbcCourse entity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcCourse.class), id);
        return entity == null? null : entity.toDomain();
    }

}
