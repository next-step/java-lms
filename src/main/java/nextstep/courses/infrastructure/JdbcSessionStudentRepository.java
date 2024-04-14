package nextstep.courses.infrastructure;

import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import nextstep.courses.infrastructure.entity.SessionStudentEntity;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcSessionStudentRepository implements SessionStudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionStudentEntity entity) {
        String sql = "insert into session_student (session_id, ns_user_id, created_at, updated_at) " +
                "values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getSessionId(), entity.getNsUserId(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }

    @Override
    public List<SessionStudentEntity> findAllBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, created_at, updated_at " +
                "from session_student " +
                "where session_id = ?";

        return jdbcTemplate.query(sql, studentEntityMapper(), sessionId);
    }

    private RowMapper<SessionStudentEntity> studentEntityMapper() {
        return (rs, rowNum) -> new SessionStudentEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                LocalDateTimeConverter.convert(rs.getTimestamp(4)),
                LocalDateTimeConverter.convert(rs.getTimestamp(5))
        );
    }

}
