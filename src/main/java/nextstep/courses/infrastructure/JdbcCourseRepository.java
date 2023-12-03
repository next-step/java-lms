package nextstep.courses.infrastructure;

import nextstep.courses.domain.entity.NsCourse;
import nextstep.courses.domain.repository.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(NsCourse nsCourse) {
        String sql = "insert into nscourse (title, creator_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, nsCourse.getTitle(), nsCourse.getCreatorId(), nsCourse.getCreatedAt());
    }

    @Override
    public NsCourse findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from nscourse where id = ?";
        RowMapper<NsCourse> rowMapper = (rs, rowNum) -> new NsCourse(
                rs.getString(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
