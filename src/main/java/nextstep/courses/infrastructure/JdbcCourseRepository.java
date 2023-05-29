package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseId;
import nextstep.courses.domain.CourseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course save(Course course) {
        return saveV1(course);
    }


    public Course saveV1(Course course) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("course").usingGeneratedKeyColumns("course_id");

        Map<String,Object> sqlParameters = new HashMap<>() {{
            put("title",course.getTitle());
            put("creator_id",course.getCreatorId());
            put("created_at",course.getCreatedAt());
            put("updated_at", course.getUpdatedAt());
        }};
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(sqlParameters));
        return new Course(
                new CourseId(key.longValue()),
                course.getTitle(),
                course.getCreatorId(),
                null,
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }



    @Deprecated(since = "이유는 모르겠지만 뭔가 문제가있음 증상은 id 가 이상한값이 반환됨")
    public Course saveV0(Course course) {
        String sql = "insert into course (title, creator_id, created_at,updated_at) values(?, ?, ?, ?)";
        // 사실 id 가 리턴되는게 아니라 rowsAffectedCount 가 리턴되는것 이였습니다.
        int courseId = jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt(), course.getUpdatedAt());
        //
        return new Course(
                new CourseId((long) courseId),
                course.getTitle(),
                course.getCreatorId(),
                null,
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }

    @Override
    public Optional<Course> findById(CourseId courseId) {
        String sql = "select * from course where course_id =" + courseId.value().toString();
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper()));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }
    //return template.queryForObject("select * from user where id=" + id, userRowMapper);


    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query("SELECT * from course", rowMapper());
    }

    @Override
    public void deleteAll() {
        //jdbcTemplate.update("delete from course");
        jdbcTemplate.update("TRUNCATE table course");
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private RowMapper<Course> rowMapper() {
        return (resultSet, rowNumber) -> {
            return new Course(
                    new CourseId(resultSet.getLong("course_id")),
                    resultSet.getString("title"),
                    resultSet.getLong("creator_id"),
                    null,
                    toLocalDateTime(resultSet.getTimestamp("created_at")),
                    toLocalDateTime(resultSet.getTimestamp("updated_at"))
            );
        };
    }
}
