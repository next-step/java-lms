package nextstep.session.infrastructure;

import nextstep.session.domain.EnrollmentRepository;
import nextstep.session.domain.StudentEnrollmentInfomation;
import nextstep.session.domain.StudentEnrollmentInfomationRepository;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StudentEnrollmentInfomationRepository")
public class JdbcStudentEnrollmentInfomationRepository implements StudentEnrollmentInfomationRepository {
	private final JdbcOperations jdbcTemplate;

	public JdbcStudentEnrollmentInfomationRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(StudentEnrollmentInfomation studentEnrollmentInfomation) {
		String sql = "insert into student_enrollment_information (enrollment_id, student_id) values(?, ?)";
		return jdbcTemplate.update(sql, studentEnrollmentInfomation.getEnrollmentId(), studentEnrollmentInfomation.getStudentId());
	}

	@Override
	public List<StudentEnrollmentInfomation> findAllByEnrollmentId(long enrollmentId) {
		String sql = "select id, enrollment_id, student_id from student_enrollment_information where enrollment_id = ?";
		RowMapper<StudentEnrollmentInfomation> rowMapper = (rs, rowNum) -> new StudentEnrollmentInfomation(
				rs.getLong(1),
				rs.getLong(2),
				rs.getLong(3));
		return jdbcTemplate.query(sql, rowMapper, enrollmentId);
	}

	@Override
	public int updateByEnrollmentId(StudentEnrollmentInfomation studentEnrollmentInfomation) {
		String sql = "update student_enrollment_information set enrollment_id = ?, student_id = ? where enrollment_id = ?";
		return jdbcTemplate.update(sql, studentEnrollmentInfomation.getEnrollmentId(), studentEnrollmentInfomation.getStudentId(), studentEnrollmentInfomation.getEnrollmentId());
	}

	@Override
	public int deleteByEnrollmentId(long id) {
		String sql = "delete from student_enrollment_information where enrollment_id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
