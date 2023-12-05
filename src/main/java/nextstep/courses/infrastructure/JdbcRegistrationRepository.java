package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.courses.domain.session.registration.Registration;
import nextstep.courses.domain.session.registration.RegistrationRepository;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {
	private final JdbcOperations jdbcTemplate;

	public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Registration> findRegistrationsBySessionId(Long sessionId) {
		String sql = "select id, user_id, session_id, created_at, updated_at from registration where session_id = ";
		RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
			toLocalDateTime(rs.getTimestamp(4)),
			toLocalDateTime(rs.getTimestamp(5)),
			rs.getLong(1),
			rs.getLong(2),
			rs.getLong(3)
			);
		return jdbcTemplate.query(sql, rowMapper, sessionId);
	}

	@Override
	public int save(Registration registration) {
		String sql = "insert into registration ("
			+ "user_id, session_id, created_at, updated_at"
			+ ") values(?, ?, ?, ?)";
		return jdbcTemplate.update(
			sql, registration.getUserId(),
			registration.getSessionId(),
			registration.getCreatedAt(),
			registration.getUpdatedAt()
		);
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}
}
