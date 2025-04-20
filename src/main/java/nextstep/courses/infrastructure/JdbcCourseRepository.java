package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.entity.CourseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(CourseEntity courseEntity) {
        String sql = "INSERT INTO course (title, deleted, creator_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, courseEntity.getTitle());
            ps.setBoolean(2, courseEntity.isDeleted());
            ps.setLong(3, courseEntity.getCreatorId());
            ps.setTimestamp(4, toTimestamp(courseEntity.getCreatedAt()));
            ps.setTimestamp(5, toTimestamp(courseEntity.getUpdatedAt()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public CourseEntity findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        RowMapper<CourseEntity> rowMapper = (rs, rowNum) -> CourseEntity.builder()
            .id(rs.getLong(1))
            .title(rs.getString(2))
            .creatorId(rs.getLong(3))
            .createdAt(toLocalDateTime(rs.getTimestamp(4)))
            .updatedAt(toLocalDateTime(rs.getTimestamp(5)))
            .build();

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private Timestamp toTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
