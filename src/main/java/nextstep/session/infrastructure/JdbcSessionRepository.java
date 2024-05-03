package nextstep.session.infrastructure;

import nextstep.session.NotFoundException;
import nextstep.session.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("SessionRepository")
public class JdbcSessionRepository implements SessionRepository {
	private final JdbcOperations jdbcTemplate;
	private final ImageInfoRepository imageInfoRepository;
	private final EnrollmentRepository enrollmentRepository;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate, ImageInfoRepository imageInfoRepository, EnrollmentRepository enrollmentRepository) {
		this.jdbcTemplate = jdbcTemplate;
		this.imageInfoRepository = imageInfoRepository;
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	public int save(Session session) {
		String sql = "insert into session (course_id, start_date, end_date) values(?, ?, ?)";
		return jdbcTemplate.update(sql, session.getCourseId(), session.getPeriod().getStartDate(), session.getPeriod().getEndDate());
	}

	@Override
	public Optional<Session> findById(long id) {
		ImageInfo imageInfo = imageInfoRepository.findBySessionId(id).orElseThrow(NotFoundException::new);
		Enrollment enrollment = enrollmentRepository.findBySessionId(id).orElseThrow(NotFoundException::new);

		String sql = "select id, course_id, start_date, end_date from session where id = ?";

		RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
				rs.getLong(1),
				rs.getLong(2),
				imageInfo,
				new Period(toLocalDateTime(rs.getTimestamp(3)), toLocalDateTime(rs.getTimestamp(4))),
				enrollment);

		return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
	}

	@Override
	public int update(Session session) {
		String sql = "update session set course_id = ?, start_date = ?, end_date = ? where id = ?";
		return jdbcTemplate.update(sql, session.getCourseId(), session.getPeriod().getStartDate(), session.getPeriod().getEndDate(), session.getId());
	}

	@Override
	public int deleteById(long id) {
		String sql = "delete from session where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}

}
