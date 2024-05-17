package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Session session) {
		String sql = "insert into session(course_id, state, image_id, start_date, end_date, number_of_student, max_number_of_student, fee) values (?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, session.getCourseId(), session.getStateString(), session.getImageId(), session.getStartDate(), session.getEndDate(), session.getNumberOfStudent(), session.getMaxNumberOfStudent(), session.getFee());
	}

	@Override
	public Session findById(Long sessionId) {
		return null;
	}

	@Override
	public int updateNumberOfStudent(int numberOfStudent) {
		return 0;
	}
}
