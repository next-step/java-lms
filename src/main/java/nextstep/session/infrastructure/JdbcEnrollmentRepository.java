package nextstep.session.infrastructure;

import nextstep.session.domain.Enrollment;
import nextstep.session.domain.EnrollmentRepository;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.StudentEnrollmentInfomationRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("EnrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
	private final JdbcOperations jdbcTemplate;
	private final StudentEnrollmentInfomationRepository studentEnrollmentInfomationRepository;
	private final UserRepository userRepository;

	public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate, StudentEnrollmentInfomationRepository studentEnrollmentInfomationRepository, UserRepository userRepository) {
		this.jdbcTemplate = jdbcTemplate;
		this.studentEnrollmentInfomationRepository = studentEnrollmentInfomationRepository;
		this.userRepository = userRepository;
	}

	@Override
	public int save(Enrollment enrollment) {
		String sql = "insert into enrollment (session_status, maximum_number_of_participants, session_price, session_id) values(?, ?, ?, ?)";
		return jdbcTemplate.update(sql, enrollment.getSessionStatus(), enrollment.getMaximumNumberOfParticipants(), enrollment.getSessionPrice(), enrollment.getSessionId());
	}

	@Override
	public Optional<Enrollment> findById(long id) {
		List<NsUser> allStudentsByStudentId = getNsUsers(id);
		String sql = "select id, maximum_number_of_participants, session_price, session_status, session_id from enrollment where id = ?";
		RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
				rs.getLong(1),
				rs.getInt(2),
				rs.getLong(3),
				rs.getLong(4),
				SessionStatus.getSessionStatus(rs.getInt(5)),
				new NsUsers(allStudentsByStudentId));
		return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
	}

	private List<NsUser> getNsUsers(long id) {
		List<Long> allStudentIdByEnrollmentId = studentEnrollmentInfomationRepository.findAllByEnrollmentId(id).stream()
				.map(s -> s.getStudentId()).collect(Collectors.toList());
		List<NsUser> allStudentsByStudentId = userRepository.findAllByStudentId(allStudentIdByEnrollmentId);
		return allStudentsByStudentId;
	}

	@Override
	public Optional<Enrollment> findBySessionId(long id) {
		List<NsUser> allStudentsByStudentId = getNsUsers(id);
		String sql = "select id, maximum_number_of_participants, session_price, session_status, session_id from enrollment where session_id = ?";
		RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
				rs.getLong(1),
				rs.getInt(2),
				rs.getLong(3),
				rs.getLong(4),
				SessionStatus.getSessionStatus(rs.getInt(5)),
				new NsUsers(allStudentsByStudentId));
		return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
	}

	@Override
	public int update(Enrollment enrollment) {
		String sql = "update enrollment set session_status = ?, maximum_number_of_participants = ?, session_price = ?, session_id = ? where id = ?";
		return jdbcTemplate.update(sql, enrollment.getSessionStatus(), enrollment.getMaximumNumberOfParticipants(), enrollment.getSessionPrice(), enrollment.getSessionId(), enrollment.getId());
	}

	@Override
	public int deleteById(long id) {
		String sql = "delete from enrollment where id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
