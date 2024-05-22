package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcUserRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<NsUser> findByUserId(String userId) {
		String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where user_id = ?";
		RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
				rs.getLong(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				toLocalDateTime(rs.getTimestamp(6)),
				toLocalDateTime(rs.getTimestamp(7)));
		return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, userId));
	}

	@Override
	public List<NsUser> findAllByStudentId(List<Long> allStudentIdByEnrollmentId) {
		if (allStudentIdByEnrollmentId == null || allStudentIdByEnrollmentId.isEmpty()) {
			return new ArrayList<>();
		}

		String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where id in (:ids)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", allStudentIdByEnrollmentId);

		RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
				rs.getLong(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				toLocalDateTime(rs.getTimestamp(6)),
				toLocalDateTime(rs.getTimestamp(7))
		);

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		return namedParameterJdbcTemplate.query(sql, parameters, rowMapper);
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}

}
