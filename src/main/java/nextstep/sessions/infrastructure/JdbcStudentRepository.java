package nextstep.sessions.infrastructure;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.exception.NotExistStudentException;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

	private final JdbcOperations jdbcTemplate;

	public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Student student) {
		String sql = "INSERT INTO student (session_id, ns_user_id, deleted, created_at) "
			+ "VALUES(?, ?, ?, ?)";
		return jdbcTemplate.update(sql, student.getSessionId(), student.getNsUserId(), student.isDeleted(), student.getCreatedAt());
	}

	@Override
	public List<Student> findBySessionId(long sessionId) {
		String sql = "select session_id, ns_user_id, deleted from student where session_id = ? and deleted = false";
		RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
			rs.getLong(1),
			rs.getLong(2),
			rs.getBoolean(3)
		);
		return jdbcTemplate.query(sql, rowMapper, sessionId);
	}

	@Override
	public Student findBySessionIdAndNsUserId(long sessionId, long nsUserId) {
		String sql = "select session_id, ns_user_id, deleted from student where session_id = ? and ns_user_id = ?";
		RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
			rs.getLong(1),
			rs.getLong(2),
			rs.getBoolean(3)
		);
		return jdbcTemplate.query(sql, rowMapper, sessionId, nsUserId).stream().findFirst()
			.orElseThrow(() -> new NotExistStudentException("없는 수강생입니다."));
	}
}
