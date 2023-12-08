package nextstep.tutor.infrastructure;

import nextstep.tutor.domain.NsTutor;
import nextstep.tutor.domain.TutorRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("tutorRepository")
public class JdbcTutorRepository implements TutorRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcTutorRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<NsTutor> findByTutorId(String tutorId) {
        String sql = "select id, tutor_id, password, name, email, created_at, updated_at from ns_tutor where tutor_id = ?";
        RowMapper<NsTutor> rowMapper = (rs, rowNum) -> new NsTutor(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, tutorId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
