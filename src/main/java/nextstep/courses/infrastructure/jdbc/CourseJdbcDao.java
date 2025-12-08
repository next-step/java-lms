package nextstep.courses.infrastructure.jdbc;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.infrastructure.entity.CourseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcDao {
    private final JdbcOperations jdbcTemplate;

    public CourseJdbcDao(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(CourseEntity entity) {
        String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getTitle(), entity.getCreatorId(), entity.getCreatedAt());
    }

    public CourseEntity findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper(), id);
    }

    private RowMapper<CourseEntity> rowMapper() {
        return (rs, rowNum) -> new CourseEntity(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getLong("creator_id"),
            toLocalDateTime(rs.getTimestamp("created_at")),
            toLocalDateTime(rs.getTimestamp("updated_at"))
        );
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}