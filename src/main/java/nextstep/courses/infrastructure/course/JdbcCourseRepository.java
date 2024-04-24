package nextstep.courses.infrastructure.course;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(final CourseEntity courseEntity) {
        final String sql = "insert into course (title, creator_id, created_at, updated_at) values(?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, courseEntity.title());
            preparedStatement.setLong(2, courseEntity.creatorId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(courseEntity.createdAt()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(courseEntity.updatedAt()));

            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<CourseEntity> findById(final Long id) {
        final String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        final RowMapper<CourseEntity> rowMapper = (resultSet, rowNumber) -> new CourseEntity(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getLong("creator_id"),
                toLocalDateTime(resultSet.getTimestamp("created_at")),
                toLocalDateTime(resultSet.getTimestamp("updated_at"))
        );

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(final Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime();
    }
}
