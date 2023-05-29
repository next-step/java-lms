package nextstep.sessions.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionDate;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.domain.Students;
import nextstep.sessions.type.StatusType;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

	private final JdbcOperations jdbcTemplate;

	private final StudentRepository studentRepository;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.studentRepository = new JdbcStudentRepository(jdbcTemplate);
	}

	@Override
	public int save(Session session) {
		String sql = "INSERT INTO session (course_id, start_at, end_at, covered_image_url, free, status_type, capacity, created_at) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, session.getCourseId(), session.getSessionDate().getStartAt(), session.getSessionDate().getEndAt(),
			session.getCoveredImageUrl(), session.isFree(), session.getEnrollment().getStatusType().toString(), session.getEnrollment().getCapacity(), session.getCreatedAt());
	}

	@Override
	public Session findById(long id) {
		String sql = "SELECT id, course_id, start_at, end_at, covered_image_url, free, status_type, capacity FROM session WHERE id = ?";
		RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
			rs.getLong("id"),
			rs.getLong("course_id"),
			new SessionDate(toLocalDateTime(rs.getTimestamp("start_at")), toLocalDateTime(rs.getTimestamp("end_at"))),
			rs.getString("covered_image_url"),
			rs.getBoolean("free"),
			StatusType.valueOf(rs.getString("status_type")),
			rs.getInt("capacity"),
			new Students(studentRepository.findBySessionId(id))
		);
		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}
}
