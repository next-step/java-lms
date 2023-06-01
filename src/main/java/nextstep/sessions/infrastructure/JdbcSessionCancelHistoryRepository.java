package nextstep.sessions.infrastructure;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import nextstep.sessions.domain.SessionCancelHistory;
import nextstep.sessions.domain.SessionCancelHistoryRepository;

@Repository("sessionCancelHistoryRepository")
public class JdbcSessionCancelHistoryRepository implements SessionCancelHistoryRepository {

	private final JdbcOperations jdbcTemplate;

	public JdbcSessionCancelHistoryRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(SessionCancelHistory sessionCancelHistory) {
		String sql = "INSERT INTO session_cancel_history (instructor_id, session_id, ns_user_id, created_at) "
			+ "VALUES(?, ?, ?, ?)";
		return jdbcTemplate.update(sql, sessionCancelHistory.getInstructor().getId(), sessionCancelHistory.getStudent().getSessionId(),
			sessionCancelHistory.getStudent().getNsUserId(), sessionCancelHistory.getCreatedAt());
	}
}
