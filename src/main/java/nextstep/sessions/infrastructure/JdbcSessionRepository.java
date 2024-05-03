package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDate;

public class JdbcSessionRepository implements SessionRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Session session) {
		String sql = "insert into session (title, creator_id, charge_status, price, session_status, capacity, started_at, ended_at) values(?, ?, ?,?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, session.getTitle(), session.getCreatorId(),session.getChargeStatus(), session.getPrice(), session.getSessionStatus(), session.getCapacity(), session.getStartedAt(), session.getEndedAt());
	}

	@Override
	public Session findById(Long id) {
		String sql = "select session.id, session.title, session.creator_id, session.charge_status, session.price, session.capacity, session.session_status,session.started_at, session.ended_at " +
				"from session where id = ?";

		RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
				rs.getLong(1),
				rs.getString(2),
				rs.getLong(3),
				ChargeStatus.valueOf(rs.getString(4)),
				rs.getInt(5),
				rs.getInt(6),
				SessionStatus.valueOf(rs.getString(7)),
				toLocalDate(rs.getTimestamp(8)),
				toLocalDate(rs.getTimestamp(9))
		);

		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	private LocalDate toLocalDate(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime().toLocalDate();
	}
}
