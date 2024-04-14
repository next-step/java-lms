package nextstep.courses.infrastructure;

import nextstep.courses.infrastructure.engine.SessionRepository;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionEntity entity) {
        String sql = "insert into session (course_id, type, status, capacity, fee, created_at, updated_at) " +
                     "values (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, entity.getCourseId(), entity.getType(),
                entity.getStatus(), entity.getCapacity(), entity.getFee(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }

    @Override
    public SessionEntity findById(Long id) {
        String sql = "select id, course_id, type, status, capacity, fee, created_at, updated_at " +
                     "from session " +
                     "where id = ?";
        return jdbcTemplate.queryForObject(sql, sessionEntityRowMapper(), id);
    }

    private RowMapper<SessionEntity> sessionEntityRowMapper() {
        return (rs, rowNum) -> new SessionEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getLong(6),
                LocalDateTimeConverter.convert(rs.getTimestamp(7)),
                LocalDateTimeConverter.convert(rs.getTimestamp(8)));
    }

}
