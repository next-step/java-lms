package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.util.List;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.RegistrationRepository;
import nextstep.courses.infrastructure.entity.RegistrationEntity;
import nextstep.courses.infrastructure.mapper.RegistrationMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Registration registration) {
        RegistrationEntity entity = RegistrationMapper.toEntity(registration);

        String sql = "insert into registration (session_id, student_id, enrolled_at) values(?, ?, ?)";

        return jdbcTemplate.update(sql,
            entity.getSessionId(),
            entity.getStudentId(),
            Timestamp.valueOf(entity.getEnrolledAt())
        );
    }

    @Override
    public Registration findById(Long id) {
        String sql = "select id, session_id, student_id, enrolled_at from registration where id = ?";

        RegistrationEntity entity = jdbcTemplate.queryForObject(sql, rowMapper(), id);
        return RegistrationMapper.toDomain(entity);
    }

    @Override
    public List<Registration> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, student_id, enrolled_at from registration where session_id = ?";

        List<RegistrationEntity> entities = jdbcTemplate.query(sql, rowMapper(), sessionId);
        return entities.stream()
            .map(RegistrationMapper::toDomain)
            .toList();
    }

    @Override
    public int countBySessionId(Long sessionId) {
        String sql = "select count(*) from registration where session_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, sessionId);
    }

    private RowMapper<RegistrationEntity> rowMapper() {
        return (rs, rowNum) -> new RegistrationEntity(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("student_id"),
            rs.getTimestamp("enrolled_at").toLocalDateTime()
        );
    }
}
