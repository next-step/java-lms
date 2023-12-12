package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.courses.domain.session.registration.Registration;
import nextstep.courses.domain.session.registration.RegistrationRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {
	private final JdbcOperations jdbcTemplate;
	private final UserRepository userRepository;

	public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.userRepository = new JdbcUserRepository(jdbcTemplate);
	}

	@Override
	public int save(Registration registration) {
		String sql = "insert into registration (user_id, session_id, approval_status, created_at, updated_at) values(?, ?, ?, ?, ?)";
		return jdbcTemplate.update(
			sql,
			registration.getUser().getId(),
			registration.getSession().getId(),
			registration.getApprovalStatus().name(),
			registration.getCreatedAt(),
			registration.getUpdatedAt()
		);
	}

	@Override
	public Optional<Registration> findById(Long id) {
		String sql = "select id, user_id, session_id, approval_status, created_at, updated_at from registration where id = ?";
		RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
			rs.getLong(1),
			null,
			null,
			ApprovalStatus.valueOf(rs.getString(4)),
			toLocalDateTime(rs.getTimestamp(5)),
			toLocalDateTime(rs.getTimestamp(6)));
		return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
	}

	@Override
	public List<Registration> findAllBySessionId(Long sessionId) {
		String sql = "select id, user_id, session_id, approval_status, created_at, updated_at from registration where session_id = ?";
		RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
			rs.getLong(1),
			findUser(rs.getLong(2)),
			null,
			ApprovalStatus.valueOf(rs.getString(4)),
			toLocalDateTime(rs.getTimestamp(5)),
			toLocalDateTime(rs.getTimestamp(6))
			);
		return jdbcTemplate.query(sql, rowMapper, sessionId);
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}

	private NsUser findUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
}
