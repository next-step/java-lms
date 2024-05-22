package nextstep.courses.infrastructure;

import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Student student) {
		String sql = "insert into student(user_id, session_id) values (?, ?)";
		return jdbcTemplate.update(sql, student.getUserId(), student.getSessionId());
	}
}
