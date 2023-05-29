package nextstep.sessions.infrastructure;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

	private final JdbcOperations jdbcTemplate;

	public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Student student) {
		String sql = "INSERT INTO student (session_id, ns_user_id, created_at) "
			+ "VALUES(?, ?, ?)";
		return jdbcTemplate.update(sql, student.getSessionId(), student.getNsUserId(), student.getCreatedAt());
	}

	@Override
	public List<Student> findBySessionId(long sessionId) {
		String sql = "select session_id, ns_user_id from student where session_id = ?";
		RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
			rs.getLong(1),
			rs.getLong(2)
		);
		return jdbcTemplate.query(sql, rowMapper, sessionId);
	}
}
