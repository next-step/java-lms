package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.entity.SessionEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static nextstep.utils.DateUtils.toLocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(SessionEntity sessionEntity) {
		String sql = "insert into session(course_id, state, image_id, start_date, end_date, number_of_student, max_number_of_student, fee) values (?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, sessionEntity.getCourseId(), sessionEntity.getState(), sessionEntity.getImageId(), sessionEntity.getStartDate(), sessionEntity.getEndDate(), sessionEntity.getNumberOfStudent(), sessionEntity.getMaxNumberOfStudent(), sessionEntity.getFee());
	}

	@Override
	public SessionEntity findById(Long sessionId) {
		String sql = "select id, course_id, state, image_id, start_date, end_date, number_of_student, max_number_of_student, fee from session where id = ?";
		RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
				rs.getLong(1),
				rs.getLong(2),
				rs.getString(3),
				rs.getLong(4),
				toLocalDateTime(rs.getTimestamp(5)),
				toLocalDateTime(rs.getTimestamp(6)),
				rs.getInt(7),
				rs.getInt(8),
				rs.getLong(9));

		return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
	}

	@Override
	public int updateNumberOfStudent(Long sessionId, int numberOfStudent) {
		String sql = "update session set number_of_student = ? where id = ?";
		return jdbcTemplate.update(sql, numberOfStudent, sessionId);
	}
}
